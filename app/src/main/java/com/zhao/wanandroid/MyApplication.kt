package com.zhao.wanandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 *创建时间： 2021/8/13
 *编   写：  zjf
 *页面功能:
 */
@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        private var instance: MyApplication? = null

        const val isDebug: Boolean = false
        fun Instance() = instance!!

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}