package com.example.hobbyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}