package com.nut.retrofit_example.utils.repo

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface CrudDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(items: List<T>)

    @Delete
    fun delete(item: T)

    fun getAll(): List<T>
    fun getById(id: Long): T?
    fun deleteById(id: Long)
    fun deleteAll()
}
