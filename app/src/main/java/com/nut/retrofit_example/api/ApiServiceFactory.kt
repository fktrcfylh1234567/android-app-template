package com.nut.retrofit_example.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceFactory {
    private const val BASE_URL = "https://httpbin.org/"

    val apiService = getServiceApi(getRetrofit())

    private fun getServiceApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}