package com.example.anmp160421012

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.anmp160421012.databinding.ActivityMainBinding
import com.example.anmp160421012.databinding.FragmentMainBinding
import java.util.Random

class MainFragment : Fragment() {
    private lateinit var binding:FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var score = requireArguments().getInt("score", 0)

        val random = Random()
        val angka1 = random.nextInt(100).toString()
        val angka2 = random.nextInt(100).toString()

        binding.txtNumber.text = angka1
        binding.txtNumber2.text = angka2

        val hasil = (angka1.toInt() + angka2.toInt()).toString()

        binding.btnSubmit.setOnClickListener {

            if (hasil == binding.txtAnswer.text.toString()){
                score++
                Log.e("score", score.toString())
                val action = MainFragmentDirections.actionMainFragmentSelf(score)
                Navigation.findNavController(it).navigate(action)
            }
            else{
                Log.e("score", score.toString())
                val action = MainFragmentDirections.actionGameFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}