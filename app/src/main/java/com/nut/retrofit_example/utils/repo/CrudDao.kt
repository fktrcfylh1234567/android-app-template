package com.nut.retrofit_example.utils.repo

import androidx.room.Delete
import androidx.room.Insert

interface CrudDao<T> {
    @Insert
    fun insert(item: T)

    @Insert
    fun insertMany(items: List<T>)

    @Delete
    fun delete(item: T)

    fun getAll(): List<T>
    fun getById(id: Long): T?
    fun deleteById(id: Long)
    fun deleteAll()
}
