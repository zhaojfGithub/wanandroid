package com.zhao.wanandroid.ui.main.activity

import com.zhao.wanandroid.network.ApiHost
import javax.inject.Inject

/**
 *创建时间： 2021/12/6
 *编   写：  zjf
 *页面功能:
 */
class MainRepository @Inject constructor() {

    suspend fun getBanner() = ApiHost.API.getBanner().getApiData()

    suspend fun getArticle(page: Int = 0) = ApiHost.API.getArticle(page).getApiData()
}