package com.zhao.wanandroid.bean

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */
data class HttpBean<T>(val errorCode: Int, val errorMsg: String, val data : T){

    fun getApiData() = if (errorCode == 0) data else throw Exception(errorMsg)
}
