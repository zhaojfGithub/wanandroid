package com.zhao.wanandroid.bean

/**
 *创建时间： 2021/12/21
 *编   写：  zjf
 *页面功能:
 */
data class OpenNumberBoxBean(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)