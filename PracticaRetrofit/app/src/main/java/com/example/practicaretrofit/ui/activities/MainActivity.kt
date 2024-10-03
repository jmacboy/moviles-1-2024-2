package com.example.practicaretrofit.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaretrofit.ui.viewmodels.MainViewModel
import com.example.practicaretrofit.R
import com.example.practicaretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.title.observe(this){
            binding.lblTitle.text = it
        }
        viewModel.body.observe(this){
            binding.lblBody.text = it
        }
    }

    private fun setupEventListeners() {
        binding.btnApiCall.setOnClickListener {
            viewModel.getPostById(1)
        }
    }
}