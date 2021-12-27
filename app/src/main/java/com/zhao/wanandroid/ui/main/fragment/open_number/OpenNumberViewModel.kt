package com.zhao.wanandroid.ui.main.fragment.open_number

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.bean.OpenNumberBoxBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.ui.main.activity.MainRepository
import com.zhao.wanandroid.utils.ExceptionUtil
import com.zhao.wanandroid.utils.LogUtils

/**
 *创建时间： 2021/12/21
 *编   写：  zjf
 *页面功能:
 */
class OpenNumberViewModel @ViewModelInject constructor(private val repository: MainRepository) : BaseViewModel() {

    val wxBoxArticle = MutableLiveData<List<OpenNumberBoxBean>>()
    val wxArticle = MutableLiveData<Pair<AppState.LoadingState, List<ArticleItemBean>>>()
    var isDataEnd = false

    private lateinit var pageArray: Array<Int>

    fun getWxParentArticle() = launch({
        isShowLoading.value = true
        wxBoxArticle.value = repository.getWxParentArticle()
        pageArray = Array(wxBoxArticle.value!!.size) { 0 }
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })


    fun getWxArticle(id: Int, index: Int,loadingState: AppState.LoadingState = AppState.LoadingState.LOAD_MORE) = launch({
        isShowLoading.value = true
        LogUtils.e("id = ${id},index = ${index}")
        when (loadingState) {
            AppState.LoadingState.REFRESH -> {
                pageArray[index] = 0
            }
            AppState.LoadingState.LOAD_MORE -> {
                isPullLoads.value = true
            }
        }
        val boxBean = repository.getWxArticle(id, pageArray[index])
        if (boxBean.data.isEmpty()){
            isDataEnd = true
        }
        pageArray[index] = pageArray[index]++
        wxArticle.value = Pair(loadingState, boxBean.data)
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        when (loadingState) {
            AppState.LoadingState.REFRESH -> {

            }
            AppState.LoadingState.LOAD_MORE -> {
                isPullLoads.value = false
            }
        }
        isShowLoading.value = false
    })

}