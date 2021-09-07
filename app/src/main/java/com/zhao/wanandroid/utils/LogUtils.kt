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

        fun w(msg: String) {
            if (MyApplication.isDebug) return
            Log.e(key, msg)
        }

        fun i(msg: String) {
            if (MyApplication.isDebug) return
            Log.i(key, msg)
        }

        fun d(msg: String) {
            if (MyApplication.isDebug) return
            Log.d(key, msg)
        }

        fun v(msg: String) {
            if (MyApplication.isDebug) return
            Log.v(key, msg)
        }

        fun e(tag: String, msg: String) {
            if (MyApplication.isDebug) return
            Log.e(tag, msg)
        }

        fun w(tag: String, msg: String) {
            if (MyApplication.isDebug) return
            Log.e(tag, msg)
        }

        fun i(tag: String, msg: String) {
            if (MyApplication.isDebug) return
            Log.i(tag, msg)
        }

        fun d(tag: String, msg: String) {
            if (MyApplication.isDebug) return
            Log.d(tag, msg)
        }

        fun v(tag: String, msg: String) {
            if (MyApplication.isDebug) return
            Log.v(tag, msg)
        }
    }
}