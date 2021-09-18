package com.zhao.wanandroid

import android.util.Log
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
        print("1"+this.javaClass.name+"\n")
        print("2"+this.javaClass.canonicalName!!+"\n")
        print("3"+this.javaClass.simpleName+"\n")
        print("4"+this.javaClass.typeName+"\n")
    }
}