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

    enum class CommonState{
        SHARE,INTEGRAL,COLLECT,TODO,PATTERN,SETTING,ME,QUIT
    }

    enum class SettingItemState{
        NONE,CHECK,COLOR
    }

    object SettingType{
        const val ITEM_NONE = 0
        const val NO_IMAGE = 1
        const val TOP_ARTICLE = 2
        const val CUT_NIGHT = 3
        const val THEME_COLOR = 4
        const val CLEAR_CACHE = 5
        const val DOWNLOAD_CODE = 6
        const val APP_VERSION = 7
        const val OFFICIAL_WEB = 8
        const val DOWNLOAD_LOG = 9
        const val CODE = 10
        const val COPYRIGHT = 11
        const val ABOUT_ME = 12
    }
}