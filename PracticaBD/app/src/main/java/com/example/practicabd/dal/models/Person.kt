package com.example.practicabd.dal.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    var name: String,
    var lastName: String,
    var age: Int,
    var city: String,
    var birthDate: String,
    var salary: Double
) {
    @PrimaryKey
    var id: Long? = null
}