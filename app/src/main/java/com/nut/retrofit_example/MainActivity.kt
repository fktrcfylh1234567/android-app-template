package com.nut.retrofit_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOk.setOnClickListener { viewModel.sendData(201) }
        btnErr.setOnClickListener { viewModel.sendData(401) }

        viewModel.title.observe(this, txtTitle::setText)
        viewModel.text.observe(this, txtResult::setText)
        viewModel.events.observe(this, ::onEvent)
    }

    private fun onEvent(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}