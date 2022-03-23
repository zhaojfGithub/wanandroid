package com.zhao.wanandroid.network

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */
object ApiHost {

    private const val BASE_URL = "https://www.wanandroid.com"

    private const val TEST_URL = "http://58.34.246.26:88/api/cstore2/"

    val API by lazy { ApiFactory.getRetrofit(BASE_URL, ApiService::class.java) }

    val TEST_API by lazy { ApiFactory.getTestRetrofit(TEST_URL,ApiService::class.java) }
}