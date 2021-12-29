package com.zhao.wanandroid.ui.main.fragment.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.bean.ArticleBoxBean
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.bean.ArticleLabelBean
import com.zhao.wanandroid.bean.BannerBean
import com.zhao.wanandroid.ui.main.activity.MainRepository
import com.zhao.wanandroid.utils.ExceptionUtil

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
        banner.value = repository.getBanner()
        val topArticleList: List<ArticleItemBean> = repository.getTopArticle()
        val articleData: ArticleBoxBean = repository.getArticle()
        topArticleList.forEach {
            (it.tags as ArrayList).add(0,ArticleLabelBean("置顶"))
        }
        (articleData.data as ArrayList).addAll(0,topArticleList)
        article.value = articleData
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun loadArticleData() = launch({
        isShowLoading.value = true
        isPullLoads.value = true
        article.value = repository.getArticle(article.value!!.curPage)
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
        isPullLoads.value = false
    })
}