package com.zhao.wanandroid.bean

import com.zhao.wanandroid.common.ApiException

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */
data class HttpBean<T>(val errorCode: Int, val errorMsg: String, val data : T){

    fun getApiData() = if (errorCode == 0) data else throw ApiException(errorMsg)
}
