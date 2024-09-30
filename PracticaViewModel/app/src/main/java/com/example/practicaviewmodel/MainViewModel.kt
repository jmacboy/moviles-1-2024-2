package com.example.practicaviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _fullName = MutableLiveData<String>().apply {
        value = ""
    }
    val fullName: LiveData<String> = _fullName

    fun setName(firstName: String, lastName: String) {
        _fullName.value = "$firstName $lastName"
    }
}