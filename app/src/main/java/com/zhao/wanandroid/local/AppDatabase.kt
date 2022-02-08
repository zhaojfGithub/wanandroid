package com.zhao.wanandroid.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zhao.wanandroid.bean.HistorySearchBean
import com.zhao.wanandroid.bean.HistorySearchDao

/**
 *创建时间： 2022/2/8
 *编   写：  zjf
 *页面功能:
 * 自动迁移玩不转啊 ，之后再说咯
 */
@Database(version = 1, entities = [HistorySearchBean::class], exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val databaseName = "WanAndroidSqlite"
    }

    abstract fun historySearchDao(): HistorySearchDao
}