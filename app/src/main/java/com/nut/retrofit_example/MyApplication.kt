package com.nut.retrofit_example

import android.app.Application
import com.nut.retrofit_example.api.ApiService
import com.nut.retrofit_example.utils.RetrofitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    private val appModule = module {
        viewModel { MainViewModel() }
        single { RetrofitFactory.create("https://httpbin.org/", ApiService::class.java) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}