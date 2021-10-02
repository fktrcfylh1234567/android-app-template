package com.nut.retrofit_example.utils

import androidx.lifecycle.MutableLiveData

/**
 * Делегат для ленивой LiveData
 *
 * Пример:
 *
 * private val _text by lazyLiveData<String> { ... }
 */
fun <T> lazyLiveData(block: () -> Unit): Lazy<MutableLiveData<T>> {
    return lazy {
        MutableLiveData<T>().apply {
            block()
        }
    }
}
