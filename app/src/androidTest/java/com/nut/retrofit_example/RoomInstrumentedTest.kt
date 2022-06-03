package com.nut.retrofit_example

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nut.retrofit_example.db.DatabaseBuilder
import com.nut.retrofit_example.db.MyEntity
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomInstrumentedTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun daoTest() = runBlocking {
        val dao = DatabaseBuilder.getInstance(context).entityDao()

        dao.insert(MyEntity(
            name = "zuzuka",
            email = "zuzuka@zuzuka.ru"
        ))

        val res = dao.getAll()
        println(res)

        dao.deleteAll()

        val res2 = dao.getAll()
        println(res2)
    }
}