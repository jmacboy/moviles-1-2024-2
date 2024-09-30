package com.example.practicaviewmodel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaviewmodel.databinding.ActivityMainBinding

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
        setupViewmodelObservers()
    }

    private fun setupViewmodelObservers() {
        viewModel.fullName.observe(this) {
            if (it.trim().isEmpty()) {
                binding.lblFullName.visibility = android.view.View.GONE
                return@observe
            }
            binding.lblFullName.apply {
                text = "El nombre completo es: $it"
                visibility = android.view.View.VISIBLE
            }
        }
    }

    private fun setupEventListeners() {
        binding.btnShowFullName.setOnClickListener {
            val firstName = binding.txtName.text.toString()
            val lastName = binding.txtLastName.text.toString()
            viewModel.setName(firstName, lastName)
        }
    }
}