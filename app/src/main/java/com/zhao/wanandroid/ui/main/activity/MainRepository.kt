package com.zhao.wanandroid.ui.main.activity

import com.zhao.wanandroid.network.ApiHost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *创建时间： 2021/12/6
 *编   写：  zjf
 *页面功能:
 */
class MainRepository @Inject constructor() {

    suspend fun getBanner() = ApiHost.API.getBanner().getApiData()

    suspend fun getTopArticle() = ApiHost.API.getTopArticle().getApiData()

    suspend fun getArticle(page: Int = 0) = ApiHost.API.getArticle(page).getApiData()

    suspend fun getPlazaArticle(page: Int = 0) = ApiHost.API.getPlazaArticle(page).getApiData()

    suspend fun getWxParentArticle() = ApiHost.API.getOpenNumberBox().getApiData()

    suspend fun getWxArticle(id: Int, page: Int = 0) = ApiHost.API.getOpenNumberItem(id, page).getApiData()

    suspend fun getSystemTree() = ApiHost.API.getSystemTree().getApiData()

    suspend fun getNavigation() = ApiHost.API.getNavigation().getApiData()

    suspend fun getProjectTree() = ApiHost.API.getProjectTree().getApiData()

    suspend fun getProjectList(id: Int, page: Int) = ApiHost.API.getProjectList(page, id).getApiData()

    suspend fun test() = ApiHost.TEST_API.loading()
}
