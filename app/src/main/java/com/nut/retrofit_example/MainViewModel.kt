package com.nut.retrofit_example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nut.retrofit_example.api.ApiService
import com.nut.retrofit_example.api.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MainViewModel : ViewModel() {

    private val _events = MutableLiveData<String>()
    val events: LiveData<String> get() = _events

    private val restApi: ApiService by inject(ApiService::class.java)

    fun getData() = viewModelScope.launch(Dispatchers.IO) {
        val msg = when (val res = restApi.get("fktrc")) {
            is Result.Success -> res.data.toString()
            is Result.Failure -> "Server response: ${res.statusCode}"
            Result.NetworkError -> "No connection to server"
        }
        _events.postValue(msg)
    }
}
