package com.zhao.wanandroid.ui.main.fragment.open_number

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.bean.LoadBean
import com.zhao.wanandroid.bean.OpenNumberBoxBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.ui.main.activity.MainRepository
import com.zhao.wanandroid.utils.ExceptionUtil

/**
 *创建时间： 2021/12/21
 *编   写：  zjf
 *页面功能:
 */
class OpenNumberViewModel @ViewModelInject constructor(private val repository: MainRepository) : BaseViewModel() {

    val wxBoxArticle = MutableLiveData<List<OpenNumberBoxBean>>()
    val wxArticle = MutableLiveData<List<ArticleItemBean>>()

    private lateinit var pageArray: Array<LoadBean>

    fun getWxParentArticle() = launch({
        isShowLoading.value = true
        wxBoxArticle.value = repository.getWxParentArticle()
        pageArray = Array(wxBoxArticle.value!!.size) { LoadBean() }
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })


    fun getWxArticle(id: Int, index: Int, loadingState: AppState.LoadingState = AppState.LoadingState.LOAD_MORE) = launch({
        if (pageArray[index].state == AppState.LoadingState.LOAD_END && loadingState == AppState.LoadingState.LOAD_MORE) return@launch
        isShowLoading.value = true
        when (loadingState) {
            AppState.LoadingState.REFRESH -> {
                pageArray[index].page = 0
                viewSate.value = AppState.LoadingState.REFRESH
            }
            AppState.LoadingState.LOAD_MORE -> {
                viewSate.value = loadingState
            }
            else -> {
                return@launch
            }
        }
        val boxBean = repository.getWxArticle(id, pageArray[index].page)
        if (boxBean.over) {
            //没有更多数据了
            pageArray[index].state = AppState.LoadingState.LOAD_END
            viewSate.value = pageArray[index].state
        } else {
            pageArray[index].page = boxBean.curPage
            wxArticle.value = boxBean.data
            viewSate.value = AppState.LoadingState.NORMAL
        }
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })
}