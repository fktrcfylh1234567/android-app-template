package com.nut.retrofit_example.utils

import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData

fun <T> AppCompatActivity.bindViewToLiveData(field: TextView, data: LiveData<T>) {
    data.observe(this) {
        field.text = it.toString()
    }
}

fun <T> AppCompatActivity.bindToastToLiveData(
    data: LiveData<T>,
    duration: Int = Toast.LENGTH_SHORT,
) {
    data.observe(this) {
        Toast.makeText(this, it.toString(), duration).show()
    }
}

fun Button.onClick(block: () -> Unit) {
    setOnClickListener { block() }
}
