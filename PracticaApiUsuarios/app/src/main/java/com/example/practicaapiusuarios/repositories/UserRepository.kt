package com.example.practicaapiusuarios.repositories

import com.example.practicaapiusuarios.api.UsersApiService
import com.example.practicaapiusuarios.models.User
import com.example.practicaapiusuarios.models.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserRepository {
    fun getUserList(
        onSuccess: (Users) -> Unit,
        onError: (Throwable) -> Unit
    ){
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(UsersApiService::class.java)
        service.getUserList().enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
    fun createUser(user: User, onSuccess: (User) -> Unit, onError: (Throwable) -> Unit){
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(UsersApiService::class.java)
        service.createUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
}