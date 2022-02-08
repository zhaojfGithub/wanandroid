package com.zhao.wanandroid.bean

import androidx.room.*
import java.util.*


/**
 *创建时间： 2022/2/7
 *编   写：  zjf
 *页面功能:
 */
data class HotSearchBean(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int,
)

@Entity(tableName = "historySearch")
data class HistorySearchBean(
    @PrimaryKey
    val text: String,
    val time: Date,
)

@Dao
interface HistorySearchDao {
    @Query("select historySearch.text from historySearch order by time desc limit 0,:number")
    suspend fun getHistorySearchList(number: Int = 10): List<String>

    @Query("replace into historySearch(text,time) values (:text,datetime('now'))")
    suspend fun insertHistorySearch(text: String)

    @Query("delete from historySearch where text = :text")
    suspend fun deleteHistorySearch(text: String)

    @Query("delete from historySearch")
    suspend fun deleteAllHistorySearch()
}