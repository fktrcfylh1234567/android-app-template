package com.nut.retrofit_example.utils

import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.nut.retrofit_example.ListActivity

/**
 * Привязывает текстовое поле к значению LiveData
 *
 * Пример:
 *
 * bindViewToLiveData(txtTitle, viewModel.title)
 */
fun <T> AppCompatActivity.bindViewToLiveData(field: TextView, data: LiveData<T>) {
    data.observe(this) {
        field.text = it.toString()
    }
}

/**
 * Выводит тосты при каждом изменении значения в LiveData
 *
 * Пример:
 *
 * bindToastToLiveData(viewModel.events)
 */
fun <T> AppCompatActivity.bindToastToLiveData(
    data: LiveData<T>,
    duration: Int = Toast.LENGTH_SHORT,
) {
    data.observe(this) {
        Toast.makeText(this, it.toString(), duration).show()
    }
}

/**
 * Обработчик нажатия на кнопку
 */
fun Button.onClick(block: () -> Unit) {
    setOnClickListener { block() }
}

/**
 * Переход на другой Activity
 *
 * Пример:
 *
 * btn.onClick { startActivityForClass(ListActivity::class.java) }
 */
fun <T> Context.startActivityForClass(cls: Class<T>) {
    startActivity(Intent(this, cls))
}
