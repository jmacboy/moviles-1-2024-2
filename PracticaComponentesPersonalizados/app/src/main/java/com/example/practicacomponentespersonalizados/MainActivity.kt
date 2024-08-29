package com.example.practicacomponentespersonalizados

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnShowValues: Button
    private lateinit var selectorNumeros: SelectorNumeros
    private lateinit var selectorNumeros2: SelectorNumeros
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnShowValues = findViewById(R.id.btnShowValues)
        selectorNumeros = findViewById(R.id.selectorNumeros)
        selectorNumeros2 = findViewById(R.id.selectorNumeros2)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnShowValues.setOnClickListener {
            Toast.makeText(
                this,
                "Selector 1: ${selectorNumeros.value}\nSelector 2: ${selectorNumeros2.value}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}