package com.example.practicaretrofit.models


typealias Comments = ArrayList<Comment>

data class Comment (
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)
