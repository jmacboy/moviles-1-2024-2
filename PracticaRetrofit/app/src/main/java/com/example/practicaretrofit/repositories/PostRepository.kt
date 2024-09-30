package com.example.practicaretrofit.repositories

import com.example.practicaretrofit.api.JSONPlaceHolderService
import com.example.practicaretrofit.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object PostRepository {
    fun getPostById(id: Int, onSuccess: (Post) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getPostById(id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
}