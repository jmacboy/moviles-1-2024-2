package com.example.practicaretrofit.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaretrofit.models.Comments
import com.example.practicaretrofit.repositories.CommentRepository
import com.example.practicaretrofit.repositories.PostRepository

class CommentListViewModel : ViewModel() {
    private val _commentList = MutableLiveData<Comments>().apply {
        value = arrayListOf()
    }
    val commentList: LiveData<Comments> = _commentList

    fun getComments(postId: Int) {
        CommentRepository.getCommentsByPost(
            postId,
            onSuccess = {
                _commentList.value = it
            }, onError = {
                it.printStackTrace()
            }
        )
    }
}