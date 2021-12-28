package com.zhao.wanandroid.ui.main.fragment.system

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.bean.SystemBean
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.ui.main.activity.MainRepository
import com.zhao.wanandroid.utils.ExceptionUtil

/**
 *创建时间： 2021/12/23
 *编   写：  zjf
 *页面功能:
 */
class SystemViewModel @ViewModelInject constructor(private var repository: MainRepository) : BaseViewModel() {

    val systemTree = MutableLiveData<List<SystemBean>>()

    fun getSystemTree() = launch({
        isShowLoading.value = true
        systemTree.value = repository.getSystemTree()
    },{
        showMsg.value = ExceptionUtil.catchException(it)
    },{
        isShowLoading.value = false
    })
}