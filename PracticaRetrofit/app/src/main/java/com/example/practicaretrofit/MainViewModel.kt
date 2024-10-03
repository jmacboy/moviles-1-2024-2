package com.example.practicaretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaretrofit.repositories.PostRepository

class MainViewModel : ViewModel() {
    private val _title = MutableLiveData<String>().apply {
        value = ""
    }
    val title: LiveData<String> = _title

    fun getPostById(id: Int) {
        PostRepository.getPostById(id,
            onSuccess = {
                _title.value = it.title
                println("Post: $it")
            }, onError = {
                println("Error: $it")
            })
    }
}