package com.nut.retrofit_example.utils

import android.content.Context
import kotlin.reflect.KProperty

// TODO
class SharedPreferencesDelegate(private val context: Context) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

fun sharedPreference(context: Context) = SharedPreferencesDelegate(context)
