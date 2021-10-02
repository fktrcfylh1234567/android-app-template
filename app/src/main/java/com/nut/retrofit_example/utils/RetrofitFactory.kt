package com.nut.retrofit_example.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    fun <T> create(baseUrl: String, service: Class<T>): T {
        val retrofit = getRetrofit(baseUrl)
        return getServiceApi(retrofit, service)
    }

    private fun <T> getServiceApi(retrofit: Retrofit, service: Class<T>) = retrofit.create(service)

    private fun getRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(ResultCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(getOkHttpClient())
        .build()

    private fun getOkHttpClient() = OkHttpClient().newBuilder().build()
}