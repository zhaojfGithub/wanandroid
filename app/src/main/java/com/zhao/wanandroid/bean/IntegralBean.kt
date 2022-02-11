package com.zhao.wanandroid.bean

import com.google.gson.annotations.SerializedName

/**
 *创建时间： 2022/2/11
 *编   写：  zjf
 *页面功能:
 */
data class IntegralBoxBean(
    val curPage: Int,
    @SerializedName("datas")
    val data: List<IntegralItemBean>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int,
)


data class IntegralItemBean(
    val coinCount: Int,
    val date: Long,
    val desc: String,
    val id: Int,
    val reason: String,
    val type: Int,
    val userId: Int,
    val userName: String,
)