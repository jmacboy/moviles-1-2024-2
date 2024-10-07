package com.example.practicaretrofit.models

data class Post (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
typealias Posts = ArrayList<Post>
