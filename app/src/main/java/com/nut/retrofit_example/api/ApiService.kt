package com.nut.retrofit_example.api

import com.nut.retrofit_example.models.HttpBinGetResponse
import com.nut.retrofit_example.utils.ResultApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("get")
    suspend fun get(): ResultApi<HttpBinGetResponse>

    @GET("/status/{code}")
    suspend fun getStatus(
        @Path("code") code: Int,
        @Query(value = "userId") userId: String,
    ): ResultApi<Unit>
}
