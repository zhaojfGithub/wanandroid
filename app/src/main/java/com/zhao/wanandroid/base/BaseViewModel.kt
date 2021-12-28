package com.zhao.wanandroid.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *创建时间： 2021/9/1
 *编   写：  zjf
 *页面功能:
 */
abstract class BaseViewModel : ViewModel() {

    protected val TAG = this.javaClass.simpleName

    val isShowLoading = MutableLiveData<Boolean>()

    val showMsg = MutableLiveData<String>()

    val isRefresh = MutableLiveData<Boolean>()

    //用于recyclerView上拉加载更多
    val isPullLoads = MutableLiveData<Boolean>()
    //
    val isLoadingEnd = MutableLiveData<Boolean>()
}