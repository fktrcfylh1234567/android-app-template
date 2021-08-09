package com.nut.retrofit_example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nut.retrofit_example.api.ApiService
import com.nut.retrofit_example.api.Result
import kotlinx.coroutines.Dispatchers
import org.koin.java.KoinJavaComponent.inject

class MainViewModel : ViewModel() {

    private val _events = MutableLiveData<String>()
    val events: LiveData<String> get() = _events

    private val restApi: ApiService by inject(ApiService::class.java)

    fun getData() = liveData(Dispatchers.IO) {
        val res = restApi.get("fktrc")
            .map { "Connected to ${it.url}.\nYour ip is ${it.origin}" }

        val msg = when (res) {
            is Result.Success -> res.data
            is Result.Failure -> "Server response: ${res.statusCode}"
            Result.NetworkError -> "No connection to server"
        }

        emit(msg)
    }
}
