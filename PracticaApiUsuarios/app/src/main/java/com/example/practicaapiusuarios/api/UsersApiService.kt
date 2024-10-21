package com.example.practicaapiusuarios.api

import com.example.practicaapiusuarios.models.User
import com.example.practicaapiusuarios.models.Users
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersApiService {
    @GET("/usuarios")
    fun getUserList(): Call<Users>

    @POST("/usuarios")
    fun createUser(@Body user: User): Call<User>
}