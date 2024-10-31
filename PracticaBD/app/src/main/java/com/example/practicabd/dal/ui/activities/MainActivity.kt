package com.example.practicabd.dal.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicabd.R
import com.example.practicabd.dal.models.Person
import com.example.practicabd.dal.ui.adapters.PersonAdapter
import com.example.practicabd.dal.ui.viewmodels.MainViewModel
import com.example.practicabd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PersonAdapter.PersonaItemListener {
    private var selectedPerson: Person? = null
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

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
        setupRecyclerView()
        setupViewModelObservers()
        setupEventListeners()

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPersons(this)
    }

    private fun setupEventListeners() {
        binding.fabGoToAddPersonForm.setOnClickListener {
            val intent = Intent(this, PersonDetailActivity::class.java)
            startActivity(intent)
        }
        binding.txtSearchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("SEARCH", newText ?: "")
                viewModel.searchByNameAndLastName(this@MainActivity, newText ?: "", newText ?: "")
                return false
            }
        })
    }

    private fun setupViewModelObservers() {
        viewModel.personList.observe(this) {
            val adapter = binding.rvPersonList.adapter as PersonAdapter
            adapter.updateData(it)
        }
    }


    private fun setupRecyclerView() {
        binding.rvPersonList.apply {
            adapter = PersonAdapter(arrayListOf(), this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        registerForContextMenu(binding.rvPersonList)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                selectedPerson?.let {
                    callEditForm(it)
                }
                true
            }

            R.id.action_delete -> {
                selectedPerson?.let {
                    viewModel.deletePerson(this, it)
                    val adapter = binding.rvPersonList.adapter as PersonAdapter
                    adapter.removeItem(it)
                    Toast.makeText(
                        this@MainActivity,
                        "Persona Eliminada Correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                true
            }

            else -> super.onContextItemSelected(item)
        }

    }

    override fun onPersonClick(person: Person) {
        callEditForm(person)
    }

    private fun callEditForm(person: Person) {
        val intent = PersonDetailActivity.intent(this, person)
        startActivity(intent)
    }

    override fun onPersonLongClick(person: Person) {
        this.selectedPerson = person
    }


}