package com.nut.retrofit_example.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


fun <T: Any> sharedPreference(context: Context, kClass: KClass<T>) = SharedPreferencesDelegate<T>(context, kClass)


// TODO
class SharedPreferencesDelegate<T: Any>(private val context: Context, private val kClass: KClass<T>) :
    ReadWriteProperty<Any?, T?> {

    private val gson = Gson()
    private val sPref: SharedPreferences by lazy {
        context.getSharedPreferences(context.packageName, MODE_PRIVATE)
    }

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        if (value == null && sPref.contains(property.name)) {
            val json = sPref.getString(property.name, null)
            value = gson.fromJson(json, kClass.java)
        }

        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        this.value = value
        val json = gson.toJson(value)
        sPref.edit().putString(property.name, json).apply()
    }
}

