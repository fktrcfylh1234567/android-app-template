package com.nut.retrofit_example.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import kotlin.reflect.KProperty


fun <T : String> sharedPreference(context: Context) = SharedPreferencesDelegate<T>(context)


// TODO
class SharedPreferencesDelegate<T : String>(private val context: Context) {

    private val sPref: SharedPreferences by lazy {
        context.getSharedPreferences(context.packageName, MODE_PRIVATE)
    }

    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        if (value == null && sPref.contains(property.name)) {
            value = sPref.getString(property.name, null) as T
        }

        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        this.value = value

        sPref.edit().putString(property.name, value.toString()).apply()
    }
}
