package com.zhao.wanandroid.ui.common

import com.zhao.wanandroid.network.ApiFactory
import com.zhao.wanandroid.network.ApiHost
import javax.inject.Inject

/**
 *创建时间： 2022/2/10
 *编   写：  zjf
 *页面功能:
 */
class CommonRepository @Inject constructor() {

    suspend fun articleShare(url: String, title: String) = ApiHost.API.articleShare(url, title).getApiData()

    suspend fun getIntegralDetail(page: Int = 1) = ApiHost.API.getIntegralDetail(page).getApiData()

    suspend fun getUserIntegral() = ApiHost.API.getUserIntegral().getApiData()
}