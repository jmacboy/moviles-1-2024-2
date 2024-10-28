package com.example.practicabd.dal.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicabd.R
import com.example.practicabd.dal.models.Person
import com.example.practicabd.dal.ui.viewmodels.PersonDetailViewModel
import com.example.practicabd.databinding.ActivityPersonDetailActiivtyBinding

class PersonDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonDetailActiivtyBinding
    private val viewModel: PersonDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonDetailActiivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnSavePerson.setOnClickListener {
            savePerson()
        }
        binding.btnCancelForm.setOnClickListener { finish() }
    }

    private fun savePerson() {
        val person = Person(
            name = binding.txtPersonName.editText?.text.toString(),
            age = binding.txtPersonAge.editText?.text.toString().toInt(),
            city = binding.txtPersonCity.editText?.text.toString(),
            lastName = binding.txtPersonLastName.editText?.text.toString(),
            birthDate = binding.txtPersonBirthDate.editText?.text.toString(),
            salary = binding.txtPersonSalary.editText?.text.toString().toDouble()
        )
        viewModel.savePerson(this, person)
        finish()
    }
}