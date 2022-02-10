package com.zhao.wanandroid.common

/**
 *创建时间： 2021/12/15
 *编   写：  zjf
 *页面功能:
 */
object AppState {

    object Adapter {
        const val ADAPTER_HEADER = 1
        const val ADAPTER_BOTTOM = 2
        const val ADAPTER_MIDDLE = 0
    }

    enum class LoadingState{
        NORMAL,REFRESH,LOAD_MORE,LOAD_END
    }
}