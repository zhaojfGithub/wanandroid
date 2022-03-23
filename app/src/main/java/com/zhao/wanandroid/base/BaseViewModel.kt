package com.zhao.wanandroid.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhao.wanandroid.common.AppState

/**
 *创建时间： 2021/9/1
 *编   写：  zjf
 *页面功能:
 */
abstract class BaseViewModel : ViewModel() {

    protected val TAG = this.javaClass.simpleName

    val isShowLoading by lazy { MutableLiveData<Boolean>() }

    val showMsg by lazy { MutableLiveData<String>() }

    val viewSate by lazy { MutableLiveData<AppState.LoadingState>() }

    val showToast by lazy { MutableLiveData<String>() }
}