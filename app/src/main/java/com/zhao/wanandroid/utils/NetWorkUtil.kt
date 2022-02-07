package com.zhao.wanandroid.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 *创建时间： 2022/2/7
 *编   写：  zjf
 *页面功能:
 */
object NetWorkUtil {

    private const val TIMEOUT = 3000

    @Suppress("DEPRECATION")
    fun isNetWorkAvailable(context: Context): Boolean {
        val manager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val info = manager.activeNetwork ?: return false
            val capabilities = manager.getNetworkCapabilities(info) ?: return false
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) return true
        } else {
            val info = manager.activeNetworkInfo
            return !(info == null || !info.isAvailable)
        }
        return false
    }
}