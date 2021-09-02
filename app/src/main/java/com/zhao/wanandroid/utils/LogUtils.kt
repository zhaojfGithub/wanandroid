package com.zhao.wanandroid.utils

import android.util.Log
import com.zhao.wanandroid.MyApplication

/**
 *创建时间： 2021/9/2
 *编   写：  zjf
 *页面功能:
 */
class LogUtils {


    companion object {

        private const val key = "MyApp"

        fun e(msg: String) {
            if (MyApplication.isDebug) return
            Log.e(key, msg)
        }
    }
}