package com.zhao.wanandroid.network

import com.zhao.wanandroid.local.SpName
import com.zhao.wanandroid.local.getSpValue
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 *创建时间： 2021/9/6
 *编   写：  zjf
 *页面功能:
 */
class AddCookiesInterceptor : Interceptor {
    /**
     * add localhost cookie
     */
    companion object {
        private const val COOKIE = "Cookie"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val list: HashSet<String> = getSpValue(SpName.cookie.COOLIE, HashSet())
        if (list.isNotEmpty()) {
            list.forEach {
                builder.addHeader(COOKIE, it)
            }
        }
        return chain.proceed(builder.build())
    }
}