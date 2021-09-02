package com.zhao.wanandroid.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *创建时间： 2021/9/1
 *编   写：  zjf
 *页面功能:
 */
abstract class BaseViewModel : ViewModel(){

    val isShowLoading = MutableLiveData<Boolean>()


}