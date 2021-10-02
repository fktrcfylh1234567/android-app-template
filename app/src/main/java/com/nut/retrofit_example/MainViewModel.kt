package com.nut.retrofit_example

import androidx.lifecycle.*
import com.nut.retrofit_example.api.ApiService
import com.nut.retrofit_example.utils.Result
import com.nut.retrofit_example.utils.lazyLiveData
import com.nut.retrofit_example.utils.sharedPreference
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MainViewModel(application: MyApplication) : AndroidViewModel(application) {

    private val restApi: ApiService by inject(ApiService::class.java)
    private var userId by sharedPreference(application, String::class)

    val title: LiveData<String> get() = _title
    private val _title by lazyLiveData<String> {
        loadIpAddress()
    }

    val text: LiveData<String> get() = _text
    private val _text = MutableLiveData<String>()

    val events: LiveData<String> get() = _events
    private val _events = MutableLiveData<String>()

    fun sendData(statusCode: Int) = viewModelScope.launch {
        val res = restApi.getStatus(statusCode, "1234")

        _text.value = when (res) {
            is Result.Success -> "Server response: ${res.data}"
            is Result.Failure -> "Server response: ${res.statusCode}"
            Result.NetworkError -> {
                _events.value = "Ошибка! Не удалось подключиться к серверу."
                return@launch
            }
        }
    }

    private fun loadIpAddress(): Job = viewModelScope.launch {
        val res = restApi.get()
        if (res !is Result.Success) {
            _events.value = "Ошибка! Не удалось подключиться к серверу."
            return@launch
        }

        _title.value = "Your ip address is " + res.data.origin
    }
}
