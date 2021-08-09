package com.nut.retrofit_example

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nut.retrofit_example.api.ApiServiceFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val restApi = ApiServiceFactory.apiService

    @Test
    fun httpbinGet() = runBlocking<Unit> {
        val res = restApi.getAsync(userId = "fktrc")
        assertTrue(res.isSuccessful)
        assertEquals(200, res.code())
        Log.d("myLogs", res.body().toString())
    }

    @Test
    fun httpbinGet404() = runBlocking<Unit> {
        val res = restApi.get404Async(code = 404, userId = "fktrc")
        assertFalse(res.isSuccessful)
        assertEquals(404, res.code())
        Log.d("myLogs", res.body().toString())
    }
}