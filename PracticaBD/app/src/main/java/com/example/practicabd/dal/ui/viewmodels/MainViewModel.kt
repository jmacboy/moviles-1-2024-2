package com.example.practicabd.dal.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicabd.dal.models.Person
import com.example.practicabd.dal.repositories.PersonRepository

class MainViewModel : ViewModel() {
    private val _personList = MutableLiveData<List<Person>>().apply {
        value = arrayListOf()
    }
    val personList: LiveData<List<Person>> = _personList

    fun loadPersons(context: Context) {
        _personList.value = PersonRepository.selectAll(context)
    }

    fun deletePerson(context: Context, person: Person) {
        PersonRepository.delete(context, person)
    }

    fun searchByNameAndLastName(context: Context, name: String, lastName: String) {
        _personList.value =
            PersonRepository.searchByNameAndLastName(context, name, lastName)
    }
}