package com.example.practicaretrofit.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaretrofit.models.Posts
import com.example.practicaretrofit.repositories.PostRepository

class PostListViewModel : ViewModel() {
    private val _postList = MutableLiveData<Posts>().apply {
        value = arrayListOf()
    }
    val postList: LiveData<Posts> = _postList

    fun getPostList() {
        PostRepository.getPostList(
            onSuccess = {
                _postList.value = it
            }, onError = {
                it.printStackTrace()
            }
        )
    }

}