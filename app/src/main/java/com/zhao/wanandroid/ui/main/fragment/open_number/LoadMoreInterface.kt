package com.zhao.wanandroid.ui.main.fragment.open_number

import com.zhao.wanandroid.common.AppState

/**
 *创建时间： 2021/12/22
 *编   写：  zjf
 *页面功能:
 */
interface LoadMoreInterface<T> {
    fun addData(loadingState: AppState.LoadingState,data: List<T>)
    fun showFooter()
}