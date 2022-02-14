package com.zhao.wanandroid.ui.common

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.bean.IntegralBoxBean
import com.zhao.wanandroid.bean.IntegralItemBean
import com.zhao.wanandroid.bean.UserIntegralBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.utils.ExceptionUtil

/**
 *创建时间： 2022/2/10
 *编   写：  zjf
 *页面功能:
 */
class CommonViewModel @ViewModelInject constructor(val repository: CommonRepository) : BaseViewModel() {

    //同理因为复用了，所有有些用不到，所以用by lazy
    val isShare by lazy { MutableLiveData<Boolean>() }
    val integralList by lazy { MutableLiveData<IntegralBoxBean>() }
    val userIntegral by lazy { MutableLiveData<UserIntegralBean>() }

    fun articleShare(title: String, link: String) = launch({
        isShowLoading.value = true
        repository.articleShare(link, title)
        isShare.value = true
        showMsg.value = MyApplication.getInstance().getString(R.string.collect_succeed)
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun getIntegralDetail(state: AppState.LoadingState = AppState.LoadingState.LOAD_MORE) = launch({
        isShowLoading.value = true
        if (integralList.value?.over == true && state == AppState.LoadingState.LOAD_MORE) return@launch
        val bean = when (state) {
            AppState.LoadingState.REFRESH -> {
                viewSate.value = AppState.LoadingState.REFRESH
                repository.getIntegralDetail()
            }
            AppState.LoadingState.LOAD_MORE -> {
                viewSate.value = AppState.LoadingState.LOAD_MORE
                repository.getIntegralDetail(integralList.value!!.curPage + 1)
            }
            else -> {
                return@launch
            }
        }
        if (bean.over) {
            viewSate.value = AppState.LoadingState.LOAD_END
        } else {
            viewSate.value = AppState.LoadingState.NORMAL
        }
        integralList.value = bean
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun getUserIntegral() = launch({
        isShowLoading.value = true
        userIntegral.value = repository.getUserIntegral()
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })
}