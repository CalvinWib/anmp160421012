package com.example.anmp160421012

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.anmp160421012.databinding.FragmentGameBinding
import com.example.anmp160421012.databinding.FragmentMainBinding

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val score = GameFragmentArgs.fromBundle(requireArguments()).score
            binding.txtTurn.text = "Your score is $score"
        }

        binding.btnBack.setOnClickListener {
            val action = GameFragmentDirections.actionMainFragment(score=0)
            Navigation.findNavController(it).navigate(action)
        }
    }
}