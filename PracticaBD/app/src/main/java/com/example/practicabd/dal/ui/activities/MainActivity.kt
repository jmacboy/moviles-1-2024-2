package com.example.practicabd.dal.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicabd.R
import com.example.practicabd.dal.ui.adapters.PersonAdapter
import com.example.practicabd.dal.ui.viewmodels.MainViewModel
import com.example.practicabd.databinding.ActivityMainBinding

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
        setupRecyclerView()
        setupViewModelObservers()
        setupEventListeners()

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPersons(this)
    }

    private fun setupEventListeners() {
        binding.fabGoToAddPersonForm.setOnClickListener {
            val intent = Intent(this, PersonDetailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.personList.observe(this) {
            val adapter = binding.rvPersonList.adapter as PersonAdapter
            adapter.updateData(it)
        }
    }


    private fun setupRecyclerView() {
        binding.rvPersonList.apply {
            adapter = PersonAdapter(arrayListOf())
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}