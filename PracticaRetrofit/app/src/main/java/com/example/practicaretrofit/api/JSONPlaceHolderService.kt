package com.example.practicaretrofit.api

import com.example.practicaretrofit.models.Comments
import com.example.practicaretrofit.models.Post
import com.example.practicaretrofit.models.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JSONPlaceHolderService {
    @GET("/posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    @GET("/posts")
    fun getPostList(): Call<Posts>

    @GET("/posts/{id}/comments")
    fun getCommentsByPost(@Path("id") id: Int): Call<Comments>
}