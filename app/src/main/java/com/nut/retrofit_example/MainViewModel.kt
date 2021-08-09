package com.nut.retrofit_example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nut.retrofit_example.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class MainViewModel : ViewModel() {

    private val restApi: ApiService by inject(ApiService::class.java)

    fun getData() = viewModelScope.launch {
        val res = getDataFromServer()
    }

    private suspend fun getDataFromServer() = withContext(Dispatchers.IO) {
        restApi.getAsync("fktrc")
    }
}
