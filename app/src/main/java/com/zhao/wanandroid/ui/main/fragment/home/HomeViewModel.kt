package com.zhao.wanandroid.ui.main.fragment.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.base.launch
import com.zhao.wanandroid.bean.ArticleBoxBean
import com.zhao.wanandroid.bean.BannerBean
import com.zhao.wanandroid.utils.ExceptionUtil
import com.zhao.wanandroid.utils.LogUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *创建时间： 2021/12/14
 *编   写：  zjf
 *页面功能:
 */
class HomeViewModel @ViewModelInject constructor(private val repository: HomeRepository) : BaseViewModel() {

    val banner = MutableLiveData<List<BannerBean>>()
    val article = MutableLiveData<ArticleBoxBean>()


    fun initHomeData() = launch({
        isShowLoading.value = true
        banner.value = repository.getBanner()
        article.value = repository.getArticle()
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun loadArticleData(isRefresh: Boolean = false) = launch({
        isShowLoading.value = true
        val page = if (isRefresh) {
            0
        } else {
            article.value!!.curPage
        }
        article.value = repository.getArticle(page)
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })
}