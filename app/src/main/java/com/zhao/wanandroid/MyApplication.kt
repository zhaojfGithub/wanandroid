package com.zhao.wanandroid

import android.app.Application
import androidx.room.Room
import com.zhao.wanandroid.local.AppDatabase
import dagger.hilt.android.HiltAndroidApp


/**
 *创建时间： 2021/8/13
 *编   写：  zjf
 *页面功能:
 */
@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        private lateinit var instance: MyApplication
        private lateinit var roomDb: AppDatabase

        fun getInstance() = instance
        fun getRoomDb() = roomDb
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        roomDb = Room.databaseBuilder(this, AppDatabase::class.java, AppDatabase.databaseName).build()
    }
}