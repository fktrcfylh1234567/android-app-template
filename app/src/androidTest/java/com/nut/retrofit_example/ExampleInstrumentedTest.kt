package com.nut.retrofit_example

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nut.retrofit_example.utils.sharedPreference
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private var field by sharedPreference<String>(context)

    @Test
    fun sharedPreferenceTest() {
        Log.d("myLogs", field.toString())
        field = "some data"
        Log.d("myLogs", field.toString())
    }
}