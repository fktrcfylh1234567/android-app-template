package com.nut.retrofit_example.db

import androidx.room.Dao
import com.nut.retrofit_example.utils.repo.CrudDao

@Dao
abstract class MyEntityDao : CrudDao<MyEntity>("MyEntity")
