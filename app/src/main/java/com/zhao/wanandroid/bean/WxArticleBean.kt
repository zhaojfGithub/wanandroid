package com.zhao.wanandroid.bean

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */
data class WxArticleBean(
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)
