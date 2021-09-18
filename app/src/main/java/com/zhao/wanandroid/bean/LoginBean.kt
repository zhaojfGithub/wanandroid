package com.zhao.wanandroid.bean

/**
 *创建时间： 2021/9/7
 *编   写：  zjf
 *页面功能:
 */
data class LoginBean(
    val admin: Boolean,
    val coinCount: Int,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)