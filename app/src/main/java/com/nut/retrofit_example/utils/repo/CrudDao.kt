package com.nut.retrofit_example.utils.repo

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

abstract class CrudDao<T>(private val tableName: String) {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMany(items: List<T>)

    @Delete
    abstract fun delete(item: T)

    @RawQuery
    protected abstract fun getAll(query: SupportSQLiteQuery): List<T>

    fun getAll(): List<T> {
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName")
        return getAll(query)
    }

    @RawQuery
    protected abstract fun getById(query: SupportSQLiteQuery): T?

    fun getById(id: Long): T? {
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName where id = $id")
        return getById(query)
    }

    @RawQuery
    protected abstract fun deleteById(query: SupportSQLiteQuery): Long

    fun deleteById(id: Long) {
        val query = SimpleSQLiteQuery("DELETE FROM $tableName WHERE id = :id")
        deleteById(query)
    }

    @RawQuery
    protected abstract fun deleteAll(query: SupportSQLiteQuery): Long

    fun deleteAll() {
        val query = SimpleSQLiteQuery("DELETE FROM $tableName")
        deleteAll(query)
    }
}
