package com.nut.retrofit_example

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nut.retrofit_example.api.ApiServiceFactory
import com.nut.retrofit_example.utils.Result
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun httpbinGet() = runBlocking<Unit> {
        val res = ApiServiceFactory.getServiceApi().get(userId = "fktrc")
        assertTrue(res is Result.Success)
        assertEquals(Result.Success("fktrc"), res.map { it.args.userId })

        res as Result.Success
        Log.d("myLogs", res.data.toString())
    }

    @Test
    fun httpbinGet404() = runBlocking<Unit> {
        val res = ApiServiceFactory.getServiceApi().getStatus(code = 404, userId = "fktrc")
        assertTrue(res is Result.Failure)

        res as Result.Failure
        assertEquals(404, res.statusCode)
    }

    @Test
    fun httpbinGetError() = runBlocking<Unit> {
        val res = ApiServiceFactory
            .getServiceApi("https://httpbin12.org/")
            .get(userId = "fktrc")
        assertTrue(res is Result.NetworkError)
    }
}