package com.example.practicaretrofit.api

import com.example.practicaretrofit.models.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JSONPlaceHolderService {
    @GET("/posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>
}