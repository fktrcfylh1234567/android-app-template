package com.nut.retrofit_example.utils

import androidx.lifecycle.MutableLiveData

fun <T> lazyLiveData(block: () -> Unit): Lazy<MutableLiveData<T>> {
    return lazy {
        MutableLiveData<T>().apply {
            block()
        }
    }
}
