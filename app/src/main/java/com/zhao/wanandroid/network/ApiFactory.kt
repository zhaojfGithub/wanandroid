package com.zhao.wanandroid.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */
object ApiFactory {

    /**
     * 连接超时时间
     */
    private const val timeOutSecond: Long = 5

    private val okHttpClient by lazy {
        OkHttpClient.Builder().retryOnConnectionFailure(true).connectTimeout(timeOutSecond, TimeUnit.SECONDS).readTimeout(timeOutSecond, TimeUnit.SECONDS)
                .writeTimeout(timeOutSecond, TimeUnit.SECONDS).addNetworkInterceptor(LogInterceptor()).addInterceptor(AddCookiesInterceptor()).build()
    }

    fun <T> getRetrofit(baseUrl: String ,clazz: Class<T>): T = Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build().create(clazz)
}