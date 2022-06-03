package com.nut.retrofit_example.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entityDao(): MyEntityDao
}
