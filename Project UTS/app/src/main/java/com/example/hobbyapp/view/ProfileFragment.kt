package com.example.hobbyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.toolbox.Volley
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnChangePass.setOnClickListener {
            val url = "http://ubaya.me/native/160421012/changePasswords"
            val firstName = binding.txtNamaDepan.text.toString()
            val lastName = binding.txtNamaBelakang.text.toString()
            val newPassword = binding.txtChangePassword.text.toString()

            changePassword(firstName, lastName, newPassword)


        }
    }

    private fun changePassword(firstName: String, lastName: String, newPassword: String) {

    }


}