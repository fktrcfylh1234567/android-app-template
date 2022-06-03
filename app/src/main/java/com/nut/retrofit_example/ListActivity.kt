package com.nut.retrofit_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.nut.retrofit_example.models.MyModel
import com.nut.retrofit_example.utils.MySimpleAdapter
import kotlinx.android.synthetic.main.activity_main2.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val viewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        viewModel.list.observe(this, ::renderList)
    }

    private fun renderList(data: List<MyModel>) {
        val mySimpleAdapter = MySimpleAdapter(this, data, R.layout.list_item, MyModel::class.java)

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
}