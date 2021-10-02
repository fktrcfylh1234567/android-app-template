package com.nut.retrofit_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.nut.retrofit_example.utils.MySimpleAdapter
import kotlinx.android.synthetic.main.activity_main2.*


class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        renderList()
    }

    private fun renderList() {
        val data = listOf(
            Model(1, "one"),
            Model(2, "two"),
            Model(3, "three"),
        )

        val mySimpleAdapter = MySimpleAdapter(this, data, R.layout.list_item, Model::class.java)

        mySimpleAdapter.colorLambda = {
            if (it.id == 2) getColor(R.color.purple_200) else null
        }

        mySimpleAdapter.viewLambda = lambda@{ it, view ->
            if (it.id != 3) return@lambda
            val txt = view.findViewById<TextView>(R.id.id)
            txt.text = "zuzuka"
        }

        linearLayout.adapter = mySimpleAdapter
    }

    data class Model(val id: Int, val name: String)
}