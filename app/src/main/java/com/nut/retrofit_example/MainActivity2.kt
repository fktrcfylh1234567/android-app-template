package com.nut.retrofit_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.nut.retrofit_example.utils.MySimpleAdapter
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        renderList()
    }

    private fun renderList() {
        val data = listOf(
            mapOf(
                "key" to "1",
                "value" to "one",
            ),
            mapOf(
                "key" to "2",
                "value" to "two",
            ),
            mapOf(
                "key" to "3",
                "value" to "three",
            )
        )

        val from = arrayOf("key", "value")
        val to = intArrayOf(R.id.txtItemText, R.id.txtItemText2)

        val mySimpleAdapter = MySimpleAdapter(this, data, R.layout.list_item, from, to)

        mySimpleAdapter.colorLambda = {
            if (it["key"] == "2") getColor(R.color.purple_200) else null
        }

        mySimpleAdapter.viewLambda = lambda@{ it, view ->
            if (it["key"] != "3") return@lambda

            val txt = view.findViewById<TextView>(R.id.txtItemText)
            txt.text = "zuzuka"
        }

        linearLayout.adapter = mySimpleAdapter
    }
}