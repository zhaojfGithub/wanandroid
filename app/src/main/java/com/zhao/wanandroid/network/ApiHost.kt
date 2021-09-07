package com.zhao.wanandroid.network

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */
object ApiHost {

    private const val BASE_URL = "https://www.wanandroid.com"

    val API by lazy { ApiFactory.getRetrofit(BASE_URL, ApiService::class.java) }
}