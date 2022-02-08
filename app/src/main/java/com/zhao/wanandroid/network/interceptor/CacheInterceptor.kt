package com.zhao.wanandroid.network.interceptor

import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.utils.NetWorkUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 *创建时间： 2022/2/7
 *编   写：  zjf
 *页面功能: 设置缓存
 */
class CacheInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetWorkUtil.isNetWorkAvailable(MyApplication.getInstance())){
            //如果没有网络
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }
        val response = chain.proceed(request)
        if (NetWorkUtil.isNetWorkAvailable(MyApplication.getInstance())){
            val maxAge = 60 * 3
            response.newBuilder()
                .header("Cache-Control","public, max-age=$maxAge")
                .removeHeader("Retrofit")
                .build()
        }else {
            val maxStale = 60 * 60 *24 * 28
            response.newBuilder()
                .header("Cache-Control","public, only-if-cached, max-stale=$maxStale")
                .removeHeader("nyn")
                .build()
        }
        return response
    }
}