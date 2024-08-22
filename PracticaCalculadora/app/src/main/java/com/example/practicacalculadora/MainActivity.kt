package com.example.practicacalculadora

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btn0: Button
    private lateinit var btnPlus: Button
    private lateinit var btnMinus: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button
    private lateinit var btnEquals: Button
    private lateinit var btnClearOne: Button
    private lateinit var btnClearEverything: Button
    private lateinit var lblResult: TextView
    private var result = ""
    private var currentOperation: OperationType = OperationType.NONE
    private var prevNumber = 0
    private var lastOpNumber = 0
    private var repeatSameOperation = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btn0 = findViewById(R.id.btn0)
        btnPlus = findViewById(R.id.btnPlus)
        btnMinus = findViewById(R.id.btnMinus)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)
        btnEquals = findViewById(R.id.btnEquals)
        btnClearOne = findViewById(R.id.btnClearOne)
        btnClearEverything = findViewById(R.id.btnClearEverything)
        lblResult = findViewById(R.id.lblResult)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btn1.setOnClickListener { appendNumber(1) }
        btn2.setOnClickListener { appendNumber(2) }
        btn3.setOnClickListener { appendNumber(3) }
        btn4.setOnClickListener { appendNumber(4) }
        btn5.setOnClickListener { appendNumber(5) }
        btn6.setOnClickListener { appendNumber(6) }
        btn7.setOnClickListener { appendNumber(7) }
        btn8.setOnClickListener { appendNumber(8) }
        btn9.setOnClickListener { appendNumber(9) }
        btn0.setOnClickListener { appendNumber(0) }
        btnPlus.setOnClickListener { startOperation(OperationType.ADDITION) }
        btnMinus.setOnClickListener { startOperation(OperationType.SUBTRACTION) }
        btnMultiply.setOnClickListener { startOperation(OperationType.MULTIPLICATION) }
        btnDivide.setOnClickListener { startOperation(OperationType.DIVISION) }
        btnEquals.setOnClickListener { performOperation() }
        btnClearOne.setOnClickListener { clearOne() }
        btnClearEverything.setOnClickListener { clearEverything() }
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
                    lblResult.text = "Error"
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
            lblResult.text = "0"
            return
        }
        lblResult.text = result
    }
}