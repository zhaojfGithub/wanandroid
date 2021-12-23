package com.zhao.wanandroid.ui.main.fragment.plaza

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.bean.ArticleBoxBean
import com.zhao.wanandroid.ui.main.activity.MainRepository
import com.zhao.wanandroid.utils.ExceptionUtil

/**
 *创建时间： 2021/12/20
 *编   写：  zjf
 *页面功能:
 */
class PlazaViewModel @ViewModelInject constructor(private val repository: MainRepository) : BaseViewModel() {

    val article = MutableLiveData<ArticleBoxBean>()

    fun refreshPlazaData() = launch(
        {
            isShowLoading.value = true
            article.value = repository.getPlazaArticle()
        }, {
            showMsg.value = ExceptionUtil.catchException(it)
        }, {
            isShowLoading.value = false
            isRefresh.value = false
        }
    )

    fun loadPlazaData() = launch(
        {
            isShowLoading.value = true
            isPullLoads.value = true
            val page = article.value?.curPage ?: 0
            article.value = repository.getPlazaArticle(page)
        }, {
            showMsg.value = ExceptionUtil.catchException(it)
        }, {
            isShowLoading.value = false
            isPullLoads.value = false
        }
    )

}