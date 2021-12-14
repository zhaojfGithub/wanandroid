package com.zhao.wanandroid.network

import com.zhao.wanandroid.bean.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */
interface ApiService {

    @GET("/wxarticle/chapters/json")
    suspend fun getWxArticle(): HttpBean<List<WxArticleBean>>

    @POST("/user/login")
    suspend fun login(@Query("username") userName: String, @Query("password") password: String): HttpBean<LoginBean>

    @POST("/user/register")
    suspend fun register(@Query("username") userName: String, @Query("password") password: String, @Query("repassword") repassWord: String): HttpBean<LoginBean>

    @GET("/user/logout/json")
    suspend fun quit(): HttpBean<List<String>>

    @GET("/banner/json")
    suspend fun getBanner(): HttpBean<List<BannerBean>>

    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page") page: Int): HttpBean<ArticleBoxBean>
}