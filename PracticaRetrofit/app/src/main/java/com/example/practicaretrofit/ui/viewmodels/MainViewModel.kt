package com.example.practicaretrofit.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaretrofit.repositories.PostRepository

class MainViewModel : ViewModel() {
    private val _title = MutableLiveData<String>().apply {
        value = ""
    }
    val title: LiveData<String> = _title
    private val _body = MutableLiveData<String>().apply {
        value = ""
    }
    val body: LiveData<String> = _body
    fun getPostById(id: Int) {
        PostRepository.getPostById(id,
            onSuccess = {
                _title.value = it.title
                _body.value = it.body
                println("Post: $it")
            }, onError = {
                println("Error: $it")
            })
    }
}