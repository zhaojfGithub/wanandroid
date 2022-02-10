package com.zhao.wanandroid.network.interceptor

import com.zhao.wanandroid.network.HttpConstant
import okhttp3.Interceptor
import okhttp3.Response

/**
 *创建时间： 2021/9/6
 *编   写：  zjf
 *页面功能:
 */
class SaveCookiesInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val requestUrl = request.url.toString()
        val domain = request.url.host
        if ((requestUrl.contains(HttpConstant.SAVE_USER_LOGIN_KEY) || requestUrl.contains(HttpConstant.SAVE_USER_REGISTER_KEY))
            && response.headers(HttpConstant.SET_COOKIE_KEY).isNotEmpty()) {
            val cookies = response.headers(HttpConstant.SET_COOKIE_KEY)
            val cookie = HttpConstant.encodeCookie(cookies)
            HttpConstant.saveCookie(requestUrl,domain,cookie)
        }
        return response
    }
}