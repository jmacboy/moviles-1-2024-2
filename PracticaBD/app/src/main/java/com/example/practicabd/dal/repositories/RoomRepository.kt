package com.example.practicabd.dal.repositories

import android.content.Context
import androidx.room.Room
import com.example.practicabd.dal.db.AppDatabase

object RoomRepository {
    fun getRoomInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "prueba-db"
        )
            .allowMainThreadQueries()
            .build()
    }
}