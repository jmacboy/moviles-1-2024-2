package com.example.practicacalculadora

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicacalculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var result = ""
    private var currentOperation: OperationType = OperationType.NONE
    private var prevNumber = 0
    private var lastOpNumber = 0
    private var repeatSameOperation = false
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
    }

    private fun setupEventListeners() {
        binding.btn1.setOnClickListener { appendNumber(1) }
        binding.btn2.setOnClickListener { appendNumber(2) }
        binding.btn3.setOnClickListener { appendNumber(3) }
        binding.btn4.setOnClickListener { appendNumber(4) }
        binding.btn5.setOnClickListener { appendNumber(5) }
        binding.btn6.setOnClickListener { appendNumber(6) }
        binding.btn7.setOnClickListener { appendNumber(7) }
        binding.btn8.setOnClickListener { appendNumber(8) }
        binding.btn9.setOnClickListener { appendNumber(9) }
        binding.btn0.setOnClickListener { appendNumber(0) }
        binding.btnPlus.setOnClickListener { startOperation(OperationType.ADDITION) }
        binding.btnMinus.setOnClickListener { startOperation(OperationType.SUBTRACTION) }
        binding.btnMultiply.setOnClickListener { startOperation(OperationType.MULTIPLICATION) }
        binding.btnDivide.setOnClickListener { startOperation(OperationType.DIVISION) }
        binding.btnEquals.setOnClickListener { performOperation() }
        binding.btnClearOne.setOnClickListener { clearOne() }
        binding.btnClearEverything.setOnClickListener { clearEverything() }
    }

    private fun clearEverything() {
        repeatSameOperation = false
        result = ""
        reloadScreen()
    }

    private fun clearOne() {
        repeatSameOperation = false
        if (result.isEmpty())
            return
        result = result.substring(0, result.length - 1)
        reloadScreen()
    }

    private fun performOperation() {
        var currentNumber = 0
        if (result.isNotEmpty()) {
            currentNumber = result.toInt()
        }
        if (repeatSameOperation) {
            currentNumber = lastOpNumber
        }
        var operationResult = 0
        when (currentOperation) {
            OperationType.ADDITION -> operationResult = prevNumber + currentNumber
            OperationType.SUBTRACTION -> operationResult = prevNumber - currentNumber
            OperationType.MULTIPLICATION -> operationResult = prevNumber * currentNumber
            OperationType.DIVISION -> {
                if (currentNumber == 0) {
                    binding.lblResult.text = "Error"
                    result = ""
                    return
                }
                operationResult = prevNumber / currentNumber
            }

            OperationType.NONE -> {}
        }
        if (!repeatSameOperation) {
            lastOpNumber = result.toInt()
        }
        result = operationResult.toString()
        prevNumber = result.toInt()

        repeatSameOperation = true
        reloadScreen()
    }

    private fun startOperation(opType: OperationType) {
        repeatSameOperation = false
        if (result.isEmpty()) {
            prevNumber = 0
        } else {
            prevNumber = result.toInt()
        }
        currentOperation = opType
        result = ""
        reloadScreen()
    }

    private fun appendNumber(number: Int) {
        result += number
        reloadScreen()
    }

    private fun reloadScreen() {
        if (result.isEmpty()) {
            binding.lblResult.text = "0"
            return
        }
        binding.lblResult.text = result
    }
}