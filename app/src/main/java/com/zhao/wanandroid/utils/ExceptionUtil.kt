package com.zhao.wanandroid.utils

import android.accounts.NetworkErrorException
import android.util.MalformedJsonException
import com.google.gson.JsonSyntaxException
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.R
import com.zhao.wanandroid.common.ApiException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 *创建时间： 2021/9/17
 *编   写：  zjf
 *页面功能:
 */
object ExceptionUtil {

    fun catchException(e: Throwable): String {
        e.printStackTrace()
        when (e) {
            is HttpException -> {
                return MyApplication.Instance().getString(R.string.err_http_msg)
            }
            is SocketTimeoutException -> {
                return MyApplication.Instance().getString(R.string.err_socket_msg)
            }
            is UnknownHostException -> {
                return MyApplication.Instance().getString(R.string.err_socket_msg)
            }
            is NetworkErrorException -> {
                return MyApplication.Instance().getString(R.string.err_network_msg)
            }
            is MalformedJsonException -> {
                return MyApplication.Instance().getString(R.string.err_json_msg)
            }
            is JsonSyntaxException -> {
                return MyApplication.Instance().getString(R.string.err_json_msg)
            }
            is ApiException -> {
                return e.toString()
            }
            is Exception -> {
                return MyApplication.Instance().getString(R.string.err_general_msg) + e.toString()
            }
            else -> {
                return MyApplication.Instance().getString(R.string.err_general_msg) + e.toString()
            }
        }
    }
}