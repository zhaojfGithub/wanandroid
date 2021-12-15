package com.zhao.wanandroid.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.base.launch
import com.zhao.wanandroid.local.SpName
import com.zhao.wanandroid.utils.ExceptionUtil
import com.zhao.wanandroid.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 *创建时间： 2021/9/2
 *编   写：  zjf
 *页面功能:
 */
class LoginViewModel @ViewModelInject constructor(private val repository: LoginRepository) : BaseViewModel() {

    // 0登录  1注册
    val type = MutableLiveData(0)

    // 0登录成功 1注册成功
    val submitType = MutableLiveData<Int>()

    val isLogin = MutableLiveData<Boolean>()

    fun updateType(type: Int) {
        this.type.value = type
    }

    fun isLogin() = launch({
        isShowLoading.value = true
        val data = repository.isLogin(SpName.cookie.COOLIE).isNotEmpty()
        LogUtils.e(data.toString())
        isLogin.value = data
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun login(userName: String, password: String) = launch({
        isShowLoading.value = true
        repository.login(userName, password)
        submitType.value = 0
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })
}