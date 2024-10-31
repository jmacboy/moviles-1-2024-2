package com.example.practicabd.dal.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicabd.dal.models.Person
import com.example.practicabd.dal.repositories.PersonRepository

class PersonDetailViewModel : ViewModel() {

    private val _person = MutableLiveData<Person>().apply {
        value = null
    }
    val person: LiveData<Person> = _person

    private val _personList = MutableLiveData<List<Person>>().apply {
        value = arrayListOf()
    }
    val personList: LiveData<List<Person>> = _personList

    fun insertPerson(context: Context, person: Person) {
        PersonRepository.insert(context, person)
    }

    fun loadPerson(context: Context, id: Long) {
        _person.value = PersonRepository.selectById(context, id)
    }

    fun updatePerson(context: Context, person: Person) {
        PersonRepository.update(context, person)
    }
    fun addPersonToList(person: Person) {
        val list = _personList.value?.toMutableList() ?: arrayListOf()
        list.add(person)
        _personList.value = list
    }
    fun removePersonFromList(person: Person) {
        val list = _personList.value?.toMutableList() ?: arrayListOf()
        list.remove(person)
        _personList.value = list
    }
}