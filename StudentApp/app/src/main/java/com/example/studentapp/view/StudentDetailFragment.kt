package com.example.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapp.R
import com.example.studentapp.databinding.FragmentStudentDetailBinding
import com.example.studentapp.viewModel.DetailViewModel
import com.example.studentapp.viewModel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding
    private val f = StudentListAdapter(arrayListOf())
    private val id = "";
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)

        viewModel.studentLD.observe(viewLifecycleOwner, Observer { student1 ->
            binding.txtId.setText(student1.id)
            binding.txtName.setText(student1.name)
            binding.txtBoD.setText(student1.dob)
            binding.txtPhone.setText(student1.phone)
        })

    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer{
        var student = it

        binding.btnUpdate.setOnClickListener{
            Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Messages", "Five seconds")
                    MainActivity.showNotif(student.name.toString(),
                        "A new notification created",
                        R.drawable.baseline_person_2_24
                        )
                }
            }
        })
    }
}