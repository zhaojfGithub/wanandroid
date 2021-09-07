package com.zhao.wanandroid.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.zhao.wanandroid.base.BaseViewModel
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

    val showChaptersData = MutableLiveData<String>()

    fun getRepository() {
        viewModelScope.launch {
            try {
                isShowLoading.value = true
                withContext(Dispatchers.IO){ repository.getChaptersData() }.data.also {
                    showChaptersData.value = Gson().toJson(it)
                }
            }catch (e:Exception){
                LogUtils.e(e.toString())
            }finally {
                isShowLoading.value = false
            }

        }
    }
}