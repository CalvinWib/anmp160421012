package com.example.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapp.R
import com.example.studentapp.databinding.FragmentCarListBinding
import com.example.studentapp.viewModel.ListViewModel

class CarListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentCarListBinding
    private val carListAdapter = CarListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = carListAdapter

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.recyclerView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            binding.refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.carListLD.observe(viewLifecycleOwner, Observer{
            carListAdapter.updateCarList(it)
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if(it==true){
                binding.recyclerView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })

        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if(it==true){
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.GONE
            }
        })
    }
}