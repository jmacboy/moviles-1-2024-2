package com.example.practicaretrofit.models

data class Post (
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String
)
typealias Posts = ArrayList<Post>
