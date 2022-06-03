package com.nut.retrofit_example.utils.repo

import com.nut.retrofit_example.utils.ResultApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UploadableRepository<T>(dao: CrudDao<T>) : BasicRepository<T>(dao) {

    protected abstract suspend fun uploadToServer(): ResultApi<Unit>

    suspend fun send(item: T): ResultApi<Unit> = withContext(Dispatchers.IO) {
        dao.insert(item)
        uploadToServer()
    }
}
