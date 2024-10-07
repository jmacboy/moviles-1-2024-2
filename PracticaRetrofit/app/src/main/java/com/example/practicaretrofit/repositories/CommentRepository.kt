package com.example.practicaretrofit.repositories

import com.example.practicaretrofit.api.JSONPlaceHolderService
import com.example.practicaretrofit.models.Comments
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CommentRepository {
    fun getCommentsByPost(
        id: Int,
        onSuccess: (Comments) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getCommentsByPost(id).enqueue(object : Callback<Comments> {
            override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
                if (response.isSuccessful) {
                    val comments = response.body()
                    onSuccess(comments!!)
                }
            }

            override fun onFailure(call: Call<Comments>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
}