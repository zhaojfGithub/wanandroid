package com.zhao.wanandroid.ui.search

import androidx.constraintlayout.motion.utils.ViewState
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.bean.*
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.local.DataState
import com.zhao.wanandroid.network.ApiHost
import com.zhao.wanandroid.utils.ExceptionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *创建时间： 2022/2/7
 *编   写：  zjf
 *页面功能:
 */
class SearchViewModel @ViewModelInject constructor(private val repository: SearchRepository) : BaseViewModel() {

    /**
     * 这样写是因为这个ViewModel复用了，在搜索详情页有的根本就用不到 所以就延迟初始化咯
     */
    val hotSearch by lazy { MutableLiveData<List<String>>() }
    val historySearch by lazy { MutableLiveData<List<String>>() }
    val searchQuery by lazy { MutableLiveData<ArticleBoxBean>() }

    val refreshBean by lazy { MutableLiveData<ArticleItemBean>() }

    fun getHotSearch() = launch({
        isShowLoading.value = true
        val data = mutableListOf<String>()
        repository.getHotSearch().forEach {
            data.add(it.name)
        }
        hotSearch.value = data
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    /**
     * 干脆把增删改写在一个一个方法里面好了
     */
    fun getHistorySearchOperate(text: String? = null, state: DataState) = launch({
        isShowLoading.value = true
        when (state) {
            DataState.SELECT -> {
                val list = repository.getHistorySearch()
                if (list.isNotEmpty()) {
                    historySearch.value = list
                }
            }
            DataState.INSERT -> {
                repository.updateHistorySearch(text ?: throw Exception("text is null?"))
            }
            DataState.UPDATE -> {
                repository.updateHistorySearch(text ?: throw Exception("text is null?"))
            }
            DataState.DELETE -> {
                repository.deleteHistorySearch(text ?: throw Exception("text is null?"))
            }
            DataState.DELETE_ALL -> {
                repository.deleteAllHistorySearch()
            }
        }
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun getSearchQuery(text: String, loadingState: AppState.LoadingState = AppState.LoadingState.LOAD_MORE) = launch({
        isShowLoading.value = true
        if (text.isEmpty() || viewSate.value ?: AppState.LoadingState.NORMAL == AppState.LoadingState.LOAD_END) return@launch
        when (loadingState) {
            AppState.LoadingState.REFRESH -> {
                viewSate.value = AppState.LoadingState.REFRESH
                searchQuery.value = repository.getSearchQuery(0, text)
            }
            AppState.LoadingState.LOAD_MORE -> {
                viewSate.value = AppState.LoadingState.LOAD_MORE
                val page = searchQuery.value?.curPage ?: 0
                val bean = repository.getSearchQuery(page, text)
                if (bean.over) {
                    viewSate.value = AppState.LoadingState.LOAD_END
                } else {
                    searchQuery.value = bean
                    viewSate.value = AppState.LoadingState.NORMAL
                }
            }
            else -> {
                return@launch
            }
        }
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun updateCollect(bean: ArticleItemBean) = launch({
        isShowLoading.value = true
        if (bean.collect) {
            repository.clearCollect(bean.id)
        } else {
            repository.insertCollect(bean.id)
        }
        //没报错说明修改成功了
        bean.collect = !bean.collect
        refreshBean.value = bean
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })
}