package com.zhao.wanandroid.utils

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 *创建时间： 2022/1/11
 *编   写：  zjf
 *页面功能:
 */
object StringUtil {

    fun getString(stream:InputStream):String{
        val reader = BufferedReader(InputStreamReader(stream,"utf-8"))
        val sb = StringBuilder()
        var s:String? = reader.readLine()
        while (s!=null){
            sb.append(s).append("\n")
            s = reader.readLine()
        }
        return sb.toString()
    }
}