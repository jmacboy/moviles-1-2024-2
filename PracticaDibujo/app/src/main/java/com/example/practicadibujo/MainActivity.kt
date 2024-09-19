package com.example.practicadibujo

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var lienzo: Lienzo
    private lateinit var btnCircle: Button
    private lateinit var btnRectangle: Button
    private lateinit var btnLine: Button
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
        btnCircle = findViewById(R.id.btnCircle)
        btnRectangle = findViewById(R.id.btnRectangle)
        btnLine = findViewById(R.id.btnLine)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnCircle.setOnClickListener {
            lienzo.setShape(ShapeType.CIRCLE)
        }
        btnRectangle.setOnClickListener {
            lienzo.setShape(ShapeType.RECTANGLE)
        }
        btnLine.setOnClickListener {
            lienzo.setShape(ShapeType.LINE)
        }
    }
}