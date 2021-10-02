package com.nut.retrofit_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nut.retrofit_example.utils.bindToastToLiveData
import com.nut.retrofit_example.utils.bindViewToLiveData
import com.nut.retrofit_example.utils.onClick
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViewToLiveData(txtTitle, viewModel.title)
        bindViewToLiveData(txtResult, viewModel.text)
        bindToastToLiveData(viewModel.events)

        btnOk.onClick { viewModel.sendData(201) }
        btnErr.onClick { viewModel.sendData(401) }
    }
}