package com.example.practicaapiusuarios.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaapiusuarios.R
import com.example.practicaapiusuarios.databinding.ActivityMainBinding
import com.example.practicaapiusuarios.ui.adapters.UserListAdapter
import com.example.practicaapiusuarios.ui.viewmodels.MainViewModel

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
        setupEventListeners()
        setupViewModelObservers()
        viewModel.loadUsers()
    }

    private fun setupEventListeners() {
        binding.fabAddUser.setOnClickListener {
            viewModel.addUser()
        }
    }

    private fun setupRecyclerView() {
        binding.rvUserList.apply {
            adapter = UserListAdapter(
                arrayListOf()
            )
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.userList.observe(this) {
            val adapter = binding.rvUserList.adapter as UserListAdapter
            adapter.updateData(it)
        }
    }
}