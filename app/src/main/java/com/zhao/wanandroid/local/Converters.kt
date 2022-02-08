package com.zhao.wanandroid.local

import androidx.room.TypeConverter
import java.util.*

/**
 *创建时间： 2022/2/8
 *编   写：  zjf
 *页面功能:
 */
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long): Date{
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date):Long{
        return date.time
    }
}