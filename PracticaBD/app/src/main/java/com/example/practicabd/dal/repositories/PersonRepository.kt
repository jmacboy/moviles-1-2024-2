package com.example.practicabd.dal.repositories

import android.content.Context
import com.example.practicabd.dal.models.Person

object PersonRepository {
    fun selectAll(context: Context): List<Person> {
        return RoomRepository.getRoomInstance(context)
            .personDAO().getAll()
    }

    fun insert(context: Context, person: Person) {
        RoomRepository.getRoomInstance(context)
            .personDAO().insert(person)
    }

    fun update(context: Context, person: Person) {
        RoomRepository.getRoomInstance(context)
            .personDAO().update(person)
    }
}