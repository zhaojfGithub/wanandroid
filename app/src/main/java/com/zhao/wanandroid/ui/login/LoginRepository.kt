package com.zhao.wanandroid.ui.login

import com.zhao.wanandroid.local.SpName
import com.zhao.wanandroid.local.getSpValue
import com.zhao.wanandroid.network.ApiHost
import com.zhao.wanandroid.utils.LogUtils
import javax.inject.Inject

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */

class LoginRepository @Inject constructor() {

    fun isLogin(key:String) = getSpValue(key,false)

    suspend fun login(userName: String, password: String) = ApiHost.API.login(userName, password)

    suspend fun register(userName: String, password: String, repassWord: String) = ApiHost.API.register(userName, password, repassWord).getApiData()
}