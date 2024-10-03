package com.example.practicaretrofit.repositories

import com.example.practicaretrofit.api.JSONPlaceHolderService
import com.example.practicaretrofit.models.Post
import com.example.practicaretrofit.models.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object PostRepository {
    fun getPostList(
        onSuccess: (Posts) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getPostList().enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }


    fun getPostById(id: Int, onSuccess: (Post) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
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