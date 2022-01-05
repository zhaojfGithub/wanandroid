package com.zhao.wanandroid.base.fragment

import com.zhao.wanandroid.common.AppState

/**
 *创建时间： 2021/12/22
 *编   写：  zjf
 *页面功能: 在viewPage嵌套多个相同逻辑的fragment时，因为livedata不可以一个activity容器内注册多次，
 * 又没必要每次都删除重新注册,所以在这里卸载其父注册，然后接口传值
 */
interface LoadMoreInterface<T> {
    fun addData(loadingState: AppState.LoadingState, data: List<T>)
    fun showFooter()
}