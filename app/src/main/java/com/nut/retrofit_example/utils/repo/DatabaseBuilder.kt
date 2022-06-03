package com.nut.retrofit_example.utils.repo

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nut.retrofit_example.db.AppDatabase

object DatabaseBuilder {
    private var INSTANCE: RoomDatabase? = null

    @Suppress("UNCHECKED_CAST")
    fun <T : RoomDatabase> getInstance(context: Context, cls: Class<T>): T {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, cls, "app-db").build()
            }
        }

        return INSTANCE!! as T
    }
}
