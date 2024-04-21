package com.example.hobbyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hobbyapp.databinding.FragmentHomeBinding
import com.example.hobbyapp.viewModel.ListViewModel

class HomeFragment : Fragment() {
    val TAG = "volleyModel"
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentHomeBinding
    private var hobbyListAdapter: HobbyListAdapter = HobbyListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        with (binding) {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = hobbyListAdapter

            refreshLayout.setOnRefreshListener {
                recyclerView.visibility = View.GONE
                txtError.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
                viewModel.refresh()
                refreshLayout.isRefreshing = false
            }
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.hobbyListLD.observe(viewLifecycleOwner, Observer { hobbyList ->
            hobbyListAdapter.updateHobbyList(hobbyList)
            binding.recyclerView.visibility = View.VISIBLE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.GONE
            binding.refreshLayout.isRefreshing = false
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                binding.recyclerView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.progressLoad.visibility = View.GONE
            }
        })

        viewModel.hobbyLoadErrorLD.observe(viewLifecycleOwner, Observer { isError ->
            if (isError) {
                binding.txtError.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
                binding.progressLoad.visibility = View.GONE
            } else {
                binding.txtError.visibility = View.GONE
            }
        })
    }

}