package com.zhao.wanandroid.bean

/**
 *创建时间： 2021/12/28
 *编   写：  zjf
 *页面功能:
 */
data class LoadBean(
    var page: Int = 0,
    var state: LoadState = LoadState.STATE,
)


enum class LoadState {
    STATE, END
}
