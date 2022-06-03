package com.nut.retrofit_example.utils.repo

import com.nut.retrofit_example.utils.ResultApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BasicRepository<T>(protected val dao: CrudDao<T>) {

    protected abstract suspend fun getFromApi(): ResultApi<List<T>>

    suspend fun getAll(): List<T> = withContext(Dispatchers.IO) {
        val res = getFromApi()

        if (res !is ResultApi.Success) {
            return@withContext dao.getAll()
        }

        dao.deleteAll()
        dao.insertMany(res.data)

        res.data
    }

    suspend fun update() {
        getAll()
    }
}