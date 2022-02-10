package com.zhao.wanandroid.ui.main.fragment.project

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.bean.LoadBean
import com.zhao.wanandroid.bean.ProjectItemBean
import com.zhao.wanandroid.bean.ProjectTreeBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.ui.main.activity.MainRepository
import com.zhao.wanandroid.utils.ExceptionUtil

/**
 *创建时间： 2022/1/5
 *编   写：  zjf
 *页面功能:
 */
class ProjectViewModel @ViewModelInject constructor(private val repository: MainRepository) : BaseViewModel() {

    val projectTree = MutableLiveData<List<ProjectTreeBean>>()
    val projectItem = MutableLiveData<List<ProjectItemBean>>()

    private lateinit var pageArray: Array<LoadBean>

    fun getProjectTree() = launch({
        isShowLoading.value = true
        val data = repository.getProjectTree()
        pageArray = Array(data.size) { LoadBean() }
        projectTree.value = data
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun getProjectItem(id: Int, index: Int, loadingState: AppState.LoadingState = AppState.LoadingState.LOAD_MORE) = launch({
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
        val data = repository.getProjectList(id, pageArray[index].page)
        if (data.over) {
            //没有更多数据了
            pageArray[index].state = AppState.LoadingState.LOAD_END
        } else {
            pageArray[index].page = data.curPage
            projectItem.value = data.data
            pageArray[index].state = AppState.LoadingState.NORMAL
        }
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

}