package com.zhao.wanandroid.bean

import com.zhao.wanandroid.common.AppState

/**
 *创建时间： 2021/12/28
 *编   写：  zjf
 *页面功能:
 */
data class LoadBean(
    var page: Int = 1,
    var state: AppState.LoadingState = AppState.LoadingState.LOAD_MORE
)
