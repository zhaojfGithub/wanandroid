package com.zhao.wanandroid.ui.search

import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.network.ApiHost
import javax.inject.Inject

/**
 *创建时间： 2022/2/7
 *编   写：  zjf
 *页面功能:
 */
class SearchRepository @Inject constructor() {

    suspend fun getHotSearch() = ApiHost.API.getHotSearchBean().getApiData()

    suspend fun getHistorySearch() = MyApplication.getRoomDb().historySearchDao().getHistorySearchList()

    suspend fun updateHistorySearch(text: String) = MyApplication.getRoomDb().historySearchDao().insertHistorySearch(text)

    suspend fun deleteHistorySearch(text: String) = MyApplication.getRoomDb().historySearchDao().deleteHistorySearch(text)

    suspend fun deleteAllHistorySearch() = MyApplication.getRoomDb().historySearchDao().deleteAllHistorySearch()

    suspend fun getSearchQuery(page: Int = 0, text: String) = ApiHost.API.getSearchQuery(page, text).getApiData()

    suspend fun insertCollect(id: Int) = ApiHost.API.insertCollect(id).getApiData()

    suspend fun clearCollect(id: Int) = ApiHost.API.clearCollect(id).getApiData()
}