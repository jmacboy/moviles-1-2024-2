package com.example.practicaretrofit

import androidx.lifecycle.ViewModel
import com.example.practicaretrofit.repositories.PostRepository

class MainViewModel : ViewModel() {
    fun getPostById(id: Int) {
        PostRepository.getPostById(id, {
            println("Post: $it")
        }, {
            println("Error: $it")
        })
    }
}