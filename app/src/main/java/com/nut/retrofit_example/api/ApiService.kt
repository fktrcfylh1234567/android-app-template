package com.nut.retrofit_example.api

import com.nut.retrofit_example.models.HttpBinGetResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("get")
    suspend fun getAsync(
        @Query(value = "userId") userId: String,
    ): Response<HttpBinGetResponse>

    @GET("/status/{code}")
    suspend fun get404Async(
        @Path("code") code: Int,
        @Query(value = "userId") userId: String,
    ): Response<HttpBinGetResponse>
}
