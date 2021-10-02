package com.nut.retrofit_example

import com.nut.retrofit_example.utils.fieldsToMap
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    data class Model(val id: Int, val name: String)

    @Test
    fun addition_isCorrect() {
        val model = Model(1, "zuzuka")
        val map = model.fieldsToMap()
        println(map)
    }
}