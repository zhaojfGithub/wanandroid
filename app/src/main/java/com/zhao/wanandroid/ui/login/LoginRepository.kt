package com.zhao.wanandroid.ui.login

import com.zhao.wanandroid.network.ApiHost
import com.zhao.wanandroid.utils.LogUtils
import javax.inject.Inject

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */

class LoginRepository @Inject constructor(){

    suspend fun getChaptersData() = ApiHost.API.getWxArticle()
}