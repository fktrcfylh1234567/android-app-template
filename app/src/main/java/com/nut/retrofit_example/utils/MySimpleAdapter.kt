package com.nut.retrofit_example.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter

// TODO
class MySimpleAdapter(
    context: Context?,
    data: MutableList<out MutableMap<String, *>>?,
    resource: Int,
    from: Array<out String>?,
    to: IntArray?
) : SimpleAdapter(context, data, resource, from, to) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return super.getView(position, convertView, parent)
    }
}