package com.zhao.wanandroid.network

import com.zhao.wanandroid.BuildConfig
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.network.interceptor.CacheInterceptor
import com.zhao.wanandroid.network.interceptor.SaveCookiesInterceptor
import com.zhao.wanandroid.network.interceptor.HeaderInterceptor
import com.zhao.wanandroid.network.interceptor.LogInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */
object ApiFactory {


    fun <T> getRetrofit(baseUrl: String ,clazz: Class<T>): T = Retrofit.Builder().baseUrl(baseUrl).client(getOkhttpClient()).addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build().create(clazz)

    fun <T> getTestRetrofit(baseUrl: String ,clazz: Class<T>): T = Retrofit.Builder().baseUrl(baseUrl).client(getTestOkhttpClient()).addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build().create(clazz)

    private fun getOkhttpClient() : OkHttpClient{
        val builder = OkHttpClient().newBuilder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG){
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        //Setting cache size and file
        val cacheFile = File(MyApplication.getInstance().cacheDir,"cache")
        val cache = Cache(cacheFile,HttpConstant.MAX_CACHE_SIZE)

        builder.run {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(HeaderInterceptor())
            addInterceptor(SaveCookiesInterceptor())
            addInterceptor(CacheInterceptor())
            cache(cache)
            connectTimeout(HttpConstant.DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            readTimeout(HttpConstant.DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            writeTimeout(HttpConstant.DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }
        return builder.build()
    }

    private fun getTestOkhttpClient() : OkHttpClient{
        val builder = OkHttpClient().newBuilder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG){
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        //Setting cache size and file
        //val cacheFile = File(MyApplication.getInstance().cacheDir,"cache")
        //val cache = Cache(cacheFile,HttpConstant.MAX_CACHE_SIZE)

        builder.run {
            addInterceptor(httpLoggingInterceptor)
            //addInterceptor(HeaderInterceptor())
            //addInterceptor(SaveCookiesInterceptor())
            //addInterceptor(CacheInterceptor())
            //cache(cache)
            connectTimeout(HttpConstant.DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            readTimeout(HttpConstant.DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            writeTimeout(HttpConstant.DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            //retryOnConnectionFailure(true)
        }
        return builder.build()
    }
}