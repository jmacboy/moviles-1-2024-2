package com.example.practicacalculadora

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicacalculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewmodel: MainViewModel by viewModels()

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
        viewmodel.result.observe(this) {
            if (it.isEmpty()) {
                binding.lblResult.text = "0"
                return@observe
            }
            binding.lblResult.text = it
        }
        viewmodel.errorMessage.observe(this) {
            if (it.isEmpty()) {
                return@observe
            }
            binding.lblResult.text = "Error"
        }
    }

    private fun setupEventListeners() {
        binding.btn1.setOnClickListener { viewmodel.appendNumber(1) }
        binding.btn2.setOnClickListener { viewmodel.appendNumber(2) }
        binding.btn3.setOnClickListener { viewmodel.appendNumber(3) }
        binding.btn4.setOnClickListener { viewmodel.appendNumber(4) }
        binding.btn5.setOnClickListener { viewmodel.appendNumber(5) }
        binding.btn6.setOnClickListener { viewmodel.appendNumber(6) }
        binding.btn7.setOnClickListener { viewmodel.appendNumber(7) }
        binding.btn8.setOnClickListener { viewmodel.appendNumber(8) }
        binding.btn9.setOnClickListener { viewmodel.appendNumber(9) }
        binding.btn0.setOnClickListener { viewmodel.appendNumber(0) }
        binding.btnPlus.setOnClickListener { viewmodel.startOperation(OperationType.ADDITION) }
        binding.btnMinus.setOnClickListener { viewmodel.startOperation(OperationType.SUBTRACTION) }
        binding.btnMultiply.setOnClickListener { viewmodel.startOperation(OperationType.MULTIPLICATION) }
        binding.btnDivide.setOnClickListener { viewmodel.startOperation(OperationType.DIVISION) }
        binding.btnEquals.setOnClickListener { viewmodel.performOperation() }
        binding.btnClearOne.setOnClickListener { viewmodel.clearOne() }
        binding.btnClearEverything.setOnClickListener { viewmodel.clearEverything() }
    }
}