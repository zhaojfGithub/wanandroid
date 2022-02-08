package com.zhao.wanandroid.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.bean.HistorySearchDao
import com.zhao.wanandroid.bean.HotSearchBean
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.local.DataState
import com.zhao.wanandroid.utils.ExceptionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *创建时间： 2022/2/7
 *编   写：  zjf
 *页面功能:
 */
class SearchViewModel @ViewModelInject constructor(private val repository: SearchRepository) : BaseViewModel() {

    val hotSearch = MutableLiveData<List<String>>()
    val historySearch = MutableLiveData<List<String>>()

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
                val list = MyApplication.getRoomDb().historySearchDao().getHistorySearchList()
                if (list.isNotEmpty()) {
                    historySearch.value = list
                }
            }
            DataState.INSERT -> {
                MyApplication.getRoomDb().historySearchDao().insertHistorySearch(text ?: throw Exception("text is null?"))
            }
            DataState.UPDATE -> {
                MyApplication.getRoomDb().historySearchDao().insertHistorySearch(text ?: throw Exception("text is null?"))
            }
            DataState.DELETE -> {
                MyApplication.getRoomDb().historySearchDao().deleteHistorySearch(text ?: throw Exception("text is null?"))
            }
            DataState.DELETE_ALL ->{
                MyApplication.getRoomDb().historySearchDao().deleteAllHistorySearch()
            }
        }
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

}