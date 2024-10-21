package com.example.practicaapiusuarios.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaapiusuarios.models.User
import com.example.practicaapiusuarios.models.Users
import com.example.practicaapiusuarios.repositories.UserRepository

class MainViewModel : ViewModel() {
    private val _userList = MutableLiveData<Users>().apply {
        value = arrayListOf()
    }
    val userList: LiveData<Users> = _userList

    fun loadUsers() {
        UserRepository.getUserList(
            onSuccess = {
                _userList.value = it
            }, onError = {
                it.printStackTrace()
            }
        )

    }

    fun addUser() {
        val user = User(
            email = "newuser2@example", // llenar en un form
            password = "123456"
        )
        UserRepository.createUser(
            user,
            onSuccess = {
                loadUsers()
            }, onError = {
                it.printStackTrace()
            }
        )
    }

}