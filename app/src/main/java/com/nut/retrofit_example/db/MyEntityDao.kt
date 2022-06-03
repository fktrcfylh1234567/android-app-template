package com.nut.retrofit_example.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.nut.retrofit_example.utils.repo.CrudDao

@Dao
interface MyEntityDao : CrudDao<MyEntity> {
    @Query("SELECT * FROM MyEntity")
    override fun getAll(): List<MyEntity>

    @Query("SELECT * FROM MyEntity where id = :id")
    override fun getById(id: Long): MyEntity?

    @Query("DELETE FROM MyEntity WHERE id = :id")
    override fun deleteById(id: Long)

    @Query("DELETE FROM MyEntity")
    override fun deleteAll()
}
