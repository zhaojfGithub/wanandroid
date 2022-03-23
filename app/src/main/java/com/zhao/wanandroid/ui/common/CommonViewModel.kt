package com.zhao.wanandroid.ui.common

import androidx.constraintlayout.motion.utils.ViewState
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.bean.*
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.network.ApiHost
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

    val allCollect by lazy { MutableLiveData<ArticleBoxBean>() }
    val collectCancelBean by lazy { MutableLiveData<ArticleItemBean>() }

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

    fun getCollectList(state: AppState.LoadingState = AppState.LoadingState.LOAD_MORE) = launch({
        isShowLoading.value = true
        if (allCollect.value?.over == true && state == AppState.LoadingState.LOAD_MORE) {
            return@launch
        }
        val page = when (state) {
            AppState.LoadingState.LOAD_MORE -> {
                viewSate.value = AppState.LoadingState.LOAD_MORE
                allCollect.value?.curPage ?: 0
            }
            AppState.LoadingState.REFRESH -> {
                viewSate.value = AppState.LoadingState.REFRESH
                0
            }
            else -> {
                return@launch
            }
        }
        val bean = repository.getCollectList(page)
        allCollect.value = bean
        if (bean.over) {
            viewSate.value = AppState.LoadingState.LOAD_END
        } else {
            viewSate.value = AppState.LoadingState.NORMAL
        }
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun cancelCollect(bean: ArticleItemBean) = launch({
        isShowLoading.value = true
        ApiHost.API.clearCollect(bean.id)
        collectCancelBean.value = bean
        showToast.value = MyApplication.getInstance().getString(R.string.collect_cancel)
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })
}