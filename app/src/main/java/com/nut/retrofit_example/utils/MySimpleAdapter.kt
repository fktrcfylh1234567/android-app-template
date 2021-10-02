package com.nut.retrofit_example.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter

class MySimpleAdapter(
    context: Context,
    private val data: List<Map<String, *>>,
    resource: Int,
    from: Array<out String>,
    to: IntArray,
) : SimpleAdapter(context, data, resource, from, to) {

    var colorLambda: ((Map<String, *>) -> Int?)? = null
    var viewLambda: ((Map<String, *>, View) -> Unit)? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getView(position, convertView, parent)
        val item = data[position]

        colorLambda?.invoke(item)?.let { view.setBackgroundColor(it) }
        viewLambda?.invoke(item, view)

        return view
    }
}

fun Any.fieldsToMap(): Map<String, Any?> {
    val cls = this::class.java
    val fields = cls.declaredFields
    return fields
        .map { field -> field.name to this.getFieldValue(field.name) }
        .toMap()
}

private fun Any.getFieldValue(name: String): Any? {
    val getterName = getterNameForFieldName(name)
    return this::class.java.declaredMethods
        .first { it.name.contains(getterName) }
        .invoke(this)
}

private fun getterNameForFieldName(name: String): String {
    return "get" + name.take(1).uppercase() + name.drop(1)
}