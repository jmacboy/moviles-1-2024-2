package com.example.practicadibujoenpantalla

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var lienzo: Lienzo
    lateinit var btnLine: Button
    lateinit var btnEraser: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lienzo = findViewById(R.id.lienzo)
        btnLine = findViewById(R.id.btnLine)
        btnEraser = findViewById(R.id.btnEraser)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnLine.setOnClickListener {
            lienzo.draw()
        }
        btnEraser.setOnClickListener {
            lienzo.erase()
        }
    }
}