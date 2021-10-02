package com.nut.retrofit_example.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter

class MySimpleAdapter<T : Any>(
    context: Context,
    private val items: List<T>,
    resource: Int,
    cls: Class<T>,
) : SimpleAdapter(context, data(items), resource, from(cls), to(context, cls)) {

    var colorLambda: ((T) -> Int?)? = null
    var viewLambda: ((T, View) -> Unit)? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getView(position, convertView, parent)
        val item = items[position]

        colorLambda?.invoke(item)?.let { view.setBackgroundColor(it) }
        viewLambda?.invoke(item, view)

        return view
    }

    companion object {
        private fun <T : Any> data(items: List<T>): List<Map<String, Any?>> {
            return items.map { it.fieldsToMap() }
        }

        private fun <T : Any> from(cls: Class<T>): Array<String> {
            return cls.declaredFields.toList().map { it.name }.toTypedArray()
        }

        private fun <T : Any> to(context: Context, cls: Class<T>): IntArray {
            val fields = cls.declaredFields.toList().map { it.name }
            return fields.map { context.getViewIdByName(it) }.toIntArray()
        }

        private fun Context.getViewIdByName(name: String): Int {
            return resources.getIdentifier(name, "id", packageName)
        }
    }
}

