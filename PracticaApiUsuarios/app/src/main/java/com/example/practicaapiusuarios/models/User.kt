package com.example.practicaapiusuarios.models

typealias Users = ArrayList<User>

data class User(
    var email: String,
    var password: String? = null
) {
    var id: Long? = null

    var createdAt: String? = null
    var updatedAt: String? = null
}
