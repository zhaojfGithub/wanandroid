package com.zhao.wanandroid.ui.main.fragment.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.bean.ArticleBoxBean
import com.zhao.wanandroid.bean.ArticleLabelBean
import com.zhao.wanandroid.bean.BannerBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.ui.main.activity.MainRepository
import com.zhao.wanandroid.utils.ExceptionUtil
import com.zhao.wanandroid.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 *创建时间： 2021/12/14
 *编   写：  zjf
 *页面功能:
 */
class HomeViewModel @ViewModelInject constructor(private val repository: MainRepository) : BaseViewModel() {

    val banner = MutableLiveData<List<BannerBean>>()
    val article = MutableLiveData<ArticleBoxBean>()


    fun initHomeData() = launch({
        isShowLoading.value = true
        val topArticleList = async { repository.getTopArticle() }
        val articleData = async { repository.getArticle() }
        val bannerBean =  async { repository.getBanner()  }
        banner.value = bannerBean.await()
        val topList = topArticleList.await().let {
            it.forEach { bean ->
                (bean.tags as ArrayList).add(0, ArticleLabelBean("置顶"))
            }
            it
        }
        article.value = articleData.await().also {
            (it.data as ArrayList).addAll(0,topList)
        }
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun loadArticleData() = launch({
        isShowLoading.value = true
        if (article.value?.over == true) return@launch
        val page: Int = article.value?.run {
            curPage
        } ?: 0
        val bean = repository.getArticle(page)
        if (bean.over) {
            viewSate.value = AppState.LoadingState.LOAD_END
        } else {
            viewSate.value = AppState.LoadingState.NORMAL
            article.value = bean
        }
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })
}