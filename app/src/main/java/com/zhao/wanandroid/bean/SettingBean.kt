package com.zhao.wanandroid.bean

import com.zhao.wanandroid.common.AppState

/**
 *创建时间： 2022/2/21
 *编   写：  zjf
 *页面功能:
 */
data class SettingBean(
    val type:Int = AppState.SettingType.ITEM_NONE,
    val title: String,
    val settingKey: String? = null,
    val details: String? = null,
    val state: AppState.SettingItemState = AppState.SettingItemState.NONE,
    var check: Boolean? = null,
    val color: Int? = null,
)
