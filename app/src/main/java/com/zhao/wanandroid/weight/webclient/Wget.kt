package com.zhao.wanandroid.weight.webclient

import okhttp3.OkHttpClient
import okhttp3.Request

/**
 *创建时间： 2022/1/11
 *编   写：  zjf
 *页面功能:
 */
object Wget {
    fun get(url: String): String {
        val client = OkHttpClient.Builder()
            .build()
        val request = Request.Builder()
            .url(url)
            .header("user-agent",
                "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3887.7 Mobile Safari/537.36")
            .build()
        val response = client.newCall(request).execute()
        return response.body?.string() ?: ""
    }
}