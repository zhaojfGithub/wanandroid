package com.zhao.wanandroid.network.interceptor

import com.zhao.wanandroid.local.SpName
import com.zhao.wanandroid.local.getSpValue
import com.zhao.wanandroid.network.HttpConstant
import okhttp3.Interceptor
import okhttp3.Response

/**
 *创建时间： 2022/2/7
 *编   写：  zjf
 *页面功能:  如果需要cookie 就把他添加上去，前提是有
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Content-type", "application/json; charset=utf-8")

        val domain = request.url.host
        val url = request.url.toString()
        if (domain.isNotEmpty() && (url.contains(HttpConstant.COLLECTIONS_WEBSITE)
                    || url.contains(HttpConstant.UNCOLLECTIONS_WEBSITE)
                    || url.contains(HttpConstant.ARTICLE_WEBSITE)
                    || url.contains(HttpConstant.TODO_WEBSITE)
                    || url.contains(HttpConstant.COIN_WEBSITE))
        ) {
            val cookie = getSpValue(domain,"")
            if (cookie.isNotEmpty()){
                builder.addHeader(HttpConstant.COOKIE_NAME,cookie)
            }
        }
        return chain.proceed(builder.build())
    }
}