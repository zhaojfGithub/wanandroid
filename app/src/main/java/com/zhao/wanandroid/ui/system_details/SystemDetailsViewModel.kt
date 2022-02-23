package com.zhao.wanandroid.ui.system_details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.bean.LoadBean
import com.zhao.wanandroid.bean.SystemBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.utils.ExceptionUtil

/**
 *创建时间： 2022/2/22
 *编   写：  zjf
 *页面功能:
 */
class SystemDetailsViewModel @ViewModelInject constructor(private val repository: SystemDetailsRepository) : BaseViewModel() {

    val initBean = MutableLiveData<SystemBean>()
    val articleItemList = MutableLiveData<List<ArticleItemBean>>()

    private lateinit var pageArray: Array<LoadBean>

    fun jsonToBean(json: String) = launch({
        isShowLoading.value = true
        val bean = Gson().fromJson(json, SystemBean::class.java)
        //这个页码是从0开始的
        pageArray = Array(bean.children.size) { LoadBean(page = 0) }
        initBean.value = bean
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })


    fun getArticle(index: Int, cid: Int, state: AppState.LoadingState = AppState.LoadingState.LOAD_MORE) = launch({
        if (state == AppState.LoadingState.LOAD_MORE && pageArray[index].state == AppState.LoadingState.LOAD_END) return@launch
        isShowLoading.value = true
        when (state) {
            AppState.LoadingState.REFRESH -> {
                pageArray[index].page = 1

            }
            AppState.LoadingState.LOAD_MORE -> {

            }
            else -> {
                return@launch
            }
        }
        viewSate.value = state
        val data = repository.getSystemArticle(pageArray[index].page, cid)
        if (data.over) {
            pageArray[index].state = AppState.LoadingState.LOAD_END
        } else {
            pageArray[index].page += 1
            pageArray[index].state = AppState.LoadingState.NORMAL
        }
        articleItemList.value = data.data
        viewSate.value = pageArray[index].state
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })
}