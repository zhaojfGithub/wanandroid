package com.zhao.wanandroid.network

import com.zhao.wanandroid.bean.HttpBean
import com.zhao.wanandroid.bean.WxArticleBean
import retrofit2.http.GET

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */
interface ApiService {

    @GET("/wxarticle/chapters/json")
    suspend fun getWxArticle() : HttpBean<List<WxArticleBean>>
}