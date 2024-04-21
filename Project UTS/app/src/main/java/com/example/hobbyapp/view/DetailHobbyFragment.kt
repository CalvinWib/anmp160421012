package com.example.hobbyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.FragmentCardHobbyBinding

class DetailHobbyFragment : Fragment() {
    private lateinit var binding: FragmentCardHobbyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardHobbyBinding.inflate(inflater,container,false)
        return binding.root
    }
}