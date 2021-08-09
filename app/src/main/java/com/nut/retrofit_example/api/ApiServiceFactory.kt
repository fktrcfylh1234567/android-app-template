package com.nut.retrofit_example.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiServiceFactory {
    private const val BASE_URL = "https://httpbin.org/"

    fun getServiceApi(baseUrl: String = BASE_URL): ApiService =
        getServiceApi(getRetrofit(baseUrl))

    private fun getServiceApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    private fun getRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(ResultCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(getOkHttpClient())
        .build()

    private fun getOkHttpClient() = OkHttpClient().newBuilder().build()
}
