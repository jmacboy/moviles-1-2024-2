package com.example.practicabd.dal.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicabd.dal.dao.PersonDAO
import com.example.practicabd.dal.models.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDAO(): PersonDAO
}