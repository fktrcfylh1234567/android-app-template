package com.nut.retrofit_example

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nut.retrofit_example.utils.sharedPreference
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SprefInstrumentedTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private var field by sharedPreference(context, String::class)
    private var fieldInt by sharedPreference(context, Int::class)

    @Test
    fun sharedPreferenceTest() {
        Log.d("myLogs", field.toString())
        field = "some data"
        Log.d("myLogs", field.toString())

        Log.d("myLogs", fieldInt.toString())
        fieldInt = 42
        Log.d("myLogs", fieldInt.toString())
    }
}