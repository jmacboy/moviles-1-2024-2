package com.example.listapersonas.ui.activities

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapersonas.R
import com.example.listapersonas.models.Contact
import com.example.listapersonas.ui.adapters.ContactAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private val datalist = arrayListOf(
        Contact("Juan Perez", "1234567890"),
        Contact("Maria Lopez", "0987654321"),
        Contact("Pedro Sanchez", "1234567890"),
        Contact("Ana Rodriguez", "0987654321"),
        Contact("Luisa Perez", "1234567890"),
        Contact("Carlos Lopez", "0987654321"),
        Contact("Jose Sanchez", "1234567890"),
        Contact("Laura Rodriguez", "0987654321"),
        Contact("Jorge Perez", "1234567890"),
        Contact("Marta Lopez", "0987654321"),
        Contact("Rosa Sanchez", "1234567890"),
        Contact("Pablo Rodriguez", "0987654321"),
    )
    private lateinit var rvContactList: RecyclerView
    private lateinit var fabAddContact: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fabAddContact = findViewById(R.id.fabAddContact)
        rvContactList = findViewById(R.id.rvContactList)
        setupRecyclerView()
        setupEventListeners()
    }

    private fun setupEventListeners() {
        fabAddContact.setOnClickListener {
            buildAlertDialog()
        }
    }

    private fun setupRecyclerView() {
        rvContactList.adapter = ContactAdapter(datalist)
        rvContactList.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
    }

    private fun buildAlertDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Agregar contacto")


        val viewInflated: View = LayoutInflater.from(this)
            .inflate(R.layout.form_layout, null, false)

        val txtNewContactName: TextView = viewInflated.findViewById(R.id.txtNewContactName)
        val txtNewContactPhone: TextView = viewInflated.findViewById(R.id.txtNewContactPhone)

        builder.setView(viewInflated)

        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            dialog.dismiss()
            val name = txtNewContactName.text.toString()
            val phone = txtNewContactPhone.text.toString()
            addContactToList(name, phone)
        }
        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun addContactToList(name: String, phone: String) {
        val contact = Contact(name, phone)
//        Toast.makeText(this@MainActivity, contact.toString(), Toast.LENGTH_SHORT).show()
        val adapter = rvContactList.adapter as ContactAdapter
        adapter.itemAdded(contact)
    }
}