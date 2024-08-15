package com.example.practicatoast

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnMessage: Button
    private lateinit var btnGoToSecond: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnMessage = findViewById(R.id.btnMessage)
        btnGoToSecond = findViewById(R.id.btnGoToSecond)
        btnMessage.setOnClickListener {

            Log.d("TITULO", getString(R.string.mensaje_de_prueba))
            val nombre = "Juan"
            Toast.makeText(
                this,
                getString(R.string.hola, nombre),
                Toast.LENGTH_SHORT
            ).show()

        }
        btnGoToSecond.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }
}