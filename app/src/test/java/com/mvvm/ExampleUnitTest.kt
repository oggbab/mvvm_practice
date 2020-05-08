package com.mvvm

import basic_test.TestActivity
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun sum() {

//        val p = Person()
//        when(p)


        val result = 4
        val a = 1
        val b = 3
        assertEquals(result, a + b)
    }


    data class Person(val name: String)
}