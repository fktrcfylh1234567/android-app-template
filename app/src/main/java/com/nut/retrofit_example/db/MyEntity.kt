package com.nut.retrofit_example.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity
data class MyEntity(
    @Expose @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "courseName") val name: String?,
    @ColumnInfo(name = "email") val email: String?,
)
