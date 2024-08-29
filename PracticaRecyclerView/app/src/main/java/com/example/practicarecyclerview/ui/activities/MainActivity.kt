package com.example.practicarecyclerview.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicarecyclerview.R
import com.example.practicarecyclerview.ui.adapters.NombresAdapter

class MainActivity : AppCompatActivity() {
    val names = arrayListOf(
        "Juan Perez",
        "Maria Garcia",
        "Pedro Lopez",
        "Jose Martinez",
        "Ana Hernandez",
        "Luis Gonzalez",
        "Laura Rodriguez",
        "Carlos Sanchez",
        "Rosa Perez",
        "Javier Ramirez",
        "Sofia Torres",
        "Jorge Diaz",
        "Marta Jimenez",
        "Alberto Vargas",
        "Elena Ruiz",
        "Raul Castro",
        "Carmen Ortega",
        "Fernando Soto",
    )
    private lateinit var rvNameList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvNameList = findViewById(R.id.rvNameList)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rvNameList.adapter = NombresAdapter(names)
        rvNameList.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
    }
}