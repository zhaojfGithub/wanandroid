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

    suspend fun getBanner() = withContext(Dispatchers.IO) { ApiHost.API.getBanner().getApiData() }

    suspend fun getTopArticle() = withContext(Dispatchers.IO) { ApiHost.API.getTopArticle().getApiData() }

    suspend fun getArticle(page: Int = 0) = withContext(Dispatchers.IO) { ApiHost.API.getArticle(page).getApiData() }

    suspend fun getPlazaArticle(page: Int = 0) = withContext(Dispatchers.IO) { ApiHost.API.getPlazaArticle(page).getApiData() }

    suspend fun getWxParentArticle() = withContext(Dispatchers.IO) { ApiHost.API.getOpenNumberBox().getApiData() }

    suspend fun getWxArticle(id: Int, page: Int = 0) = withContext(Dispatchers.IO) { ApiHost.API.getOpenNumberItem(id, page).getApiData() }

    suspend fun getSystemTree() = withContext(Dispatchers.IO){ ApiHost.API.getSystemTree().getApiData() }

    suspend fun getNavigation() = withContext(Dispatchers.IO){ ApiHost.API.getNavigation().getApiData() }

    suspend fun getProjectTree() = withContext(Dispatchers.IO){ ApiHost.API.getProjectTree().getApiData() }

    suspend fun getProjectList(id: Int,page: Int) = withContext(Dispatchers.IO) { ApiHost.API.getProjectList(page,id).getApiData()  }
}
