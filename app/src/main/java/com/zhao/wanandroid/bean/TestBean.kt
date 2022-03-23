package com.zhao.wanandroid.bean

import com.google.gson.annotations.SerializedName

/**
 *创建时间： 2022/3/14
 *编   写：  zjf
 *页面功能:
 */
data class TestBean(
    @SerializedName("storeid")
    val storeId:String = "S15103",
    val userId:String = "99999991",
    val passWord:String = "0000"
)
