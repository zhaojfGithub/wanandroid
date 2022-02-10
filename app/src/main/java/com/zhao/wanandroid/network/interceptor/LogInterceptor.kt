package com.zhao.wanandroid.network.interceptor

import com.zhao.wanandroid.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */
class LogInterceptor : Interceptor {

    companion object {
        private const val TAG = "LogInterceptor"
    }
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val startTime = System.nanoTime()
        LogUtils.v(TAG,"url = ${request.url} , ${chain.connection()} , ${request.headers}")

        val response = chain.proceed(request)
        val list : HashSet<String> = HashSet()
        response.headers("Set-Cookie").forEach {
            LogUtils.v(TAG,"cookies=${it}")
            list.add(it)
        }
        //putSpValue(SpName.cookie.COOLIE,list)

        val endTime = System.nanoTime()
        LogUtils.v(TAG,"${response.request.url} , ${(endTime - startTime) / 1000000 }ms . ${response.headers} , ${response.message}")

        val body = response.body
        LogUtils.v(TAG,"contentLength:${body?.contentLength()}")
        return response
    }
}