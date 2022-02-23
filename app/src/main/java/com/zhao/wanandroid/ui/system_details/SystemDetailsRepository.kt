package com.zhao.wanandroid.ui.system_details

import com.zhao.wanandroid.network.ApiFactory
import com.zhao.wanandroid.network.ApiHost
import javax.inject.Inject

/**
 *创建时间： 2022/2/22
 *编   写：  zjf
 *页面功能:
 */
class SystemDetailsRepository @Inject constructor() {

    suspend fun getSystemArticle(page: Int = 0, cid: Int) = ApiHost.API.getSystemArticle(page, cid).getApiData()
}