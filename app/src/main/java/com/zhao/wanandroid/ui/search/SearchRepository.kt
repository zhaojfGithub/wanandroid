package com.zhao.wanandroid.ui.search

import com.zhao.wanandroid.network.ApiHost
import javax.inject.Inject

/**
 *创建时间： 2022/2/7
 *编   写：  zjf
 *页面功能:
 */
class SearchRepository @Inject constructor(){

    suspend fun getHotSearch() = ApiHost.API.getHotSearchBean().getApiData()
}