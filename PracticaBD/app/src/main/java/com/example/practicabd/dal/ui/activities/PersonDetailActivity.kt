package com.example.practicabd.dal.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicabd.R
import com.example.practicabd.dal.models.Person
import com.example.practicabd.dal.ui.adapters.PersonAdapter
import com.example.practicabd.dal.ui.viewmodels.PersonDetailViewModel
import com.example.practicabd.databinding.ActivityPersonDetailActiivtyBinding

class PersonDetailActivity : AppCompatActivity() {
    private var id: Long = -1
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
        id = intent.getLongExtra(PERSON_ID, -1)
        loadPerson(id)
        setupViewModelObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvPersonDetailList.apply {
            adapter = PersonAdapter(arrayListOf(), null)
            layoutManager = LinearLayoutManager(this@PersonDetailActivity)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.person.observe(this) {
            if (it == null) return@observe
            binding.txtPersonName.editText?.setText(it.name)
            binding.txtPersonLastName.editText?.setText(it.lastName)
            binding.txtPersonAge.editText?.setText(it.age.toString())
            binding.txtPersonCity.editText?.setText(it.city)
            binding.txtPersonBirthDate.editText?.setText(it.birthDate)
            binding.txtPersonSalary.editText?.setText(it.salary.toString())
        }
        viewModel.personList.observe(this) {
            val adapter = binding.rvPersonDetailList.adapter as PersonAdapter
            adapter.updateData(it)
        }
    }

    private fun loadPerson(id: Long) {
        if (id == -1L) return;
        viewModel.loadPerson(this, id)
    }

    private fun setupEventListeners() {
        binding.btnSavePerson.setOnClickListener {
            savePerson()
        }
        binding.btnCancelForm.setOnClickListener { finish() }
        binding.btnAddItem.setOnClickListener {
            val person = Person(
                name = binding.txtPersonName.editText?.text.toString(),
                age = binding.txtPersonAge.editText?.text.toString().toInt(),
                city = binding.txtPersonCity.editText?.text.toString(),
                lastName = binding.txtPersonLastName.editText?.text.toString(),
                birthDate = binding.txtPersonBirthDate.editText?.text.toString(),
                salary = binding.txtPersonSalary.editText?.text.toString().toDouble()
            )
            viewModel.addPersonToList(person)
        }
        binding.btnDeleteItem.setOnClickListener {
            val lastPersonFromlist = viewModel.personList.value?.lastOrNull()
            lastPersonFromlist?.let {
                viewModel.removePersonFromList(lastPersonFromlist)
            }
        }
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
        if (id != -1L) {
            person.id = id
            viewModel.updatePerson(this, person)
        } else {
            viewModel.insertPerson(this, person)
        }
        finish()
    }

    companion object {
        private const val PERSON_ID = "PERSON_ID"

        fun intent(context: Context, person: Person): Intent {
            return Intent(context, PersonDetailActivity::class.java).apply {
                putExtra(PERSON_ID, person.id)
            }
        }
    }
}