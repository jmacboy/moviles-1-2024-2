package com.example.practicafragments.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listapersonas.models.Contact
import com.example.practicafragments.R
import com.example.practicafragments.databinding.ActivityMainBinding
import com.example.practicafragments.ui.fragments.BottomFragment
import com.example.practicafragments.ui.fragments.TopFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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
    }


    fun clearForm() {
        val topFragment = binding.fragmentContainerView.getFragment<TopFragment>()
        topFragment.clearForm()
    }

    fun saveContact(contact: Contact, isInsert: Boolean) {
        val bottomFragment = binding.fragmentContainerView2.getFragment<BottomFragment>()
        if (isInsert) {
            bottomFragment.addContactToList(contact.name, contact.phone)
        } else {
            bottomFragment.editContactFromList(contact)
        }
        clearForm()
    }

    fun editContact(contact: Contact) {
        val topFragment = binding.fragmentContainerView.getFragment<TopFragment>()
        topFragment.editContact(contact)
    }

}