package com.nut.retrofit_example

import androidx.lifecycle.*
import com.nut.retrofit_example.api.ApiService
import com.nut.retrofit_example.models.MyModel
import com.nut.retrofit_example.utils.lazyLiveData
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ListViewModel(application: MyApplication) : AndroidViewModel(application) {
    private val restApi: ApiService by inject(ApiService::class.java)

    val list: LiveData<List<MyModel>> get() = _list
    private val _list by lazyLiveData<List<MyModel>> {
        loadList()
    }

    private fun loadList(): Any = viewModelScope.launch {
        _list.value = listOf(
            MyModel(1, "one"),
            MyModel(2, "two"),
            MyModel(3, "three"),
        )
    }
}
