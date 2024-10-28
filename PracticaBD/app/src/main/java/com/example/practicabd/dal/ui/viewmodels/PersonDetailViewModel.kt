package com.example.practicabd.dal.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.practicabd.dal.models.Person
import com.example.practicabd.dal.repositories.PersonRepository

class PersonDetailViewModel : ViewModel() {
    fun savePerson(context: Context, person: Person) {
        PersonRepository.insert(context, person)
    }
}