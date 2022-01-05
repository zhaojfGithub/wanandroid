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

    @GET("/article/list/{page}/json")
    suspend fun getArticle(@Path("page") page: Int): HttpBean<ArticleBoxBean>

    @GET("/article/top/json")
    suspend fun getTopArticle(): HttpBean<List<ArticleItemBean>>

    @GET("/user_article/list/{page}/json")
    suspend fun getPlazaArticle(@Path("page") page: Int): HttpBean<ArticleBoxBean>

    @GET("/wxarticle/chapters/json")
    suspend fun getOpenNumberBox(): HttpBean<List<OpenNumberBoxBean>>

    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getOpenNumberItem(@Path("id") id: Int, @Path("page") page: Int): HttpBean<ArticleBoxBean>

    @GET("/tree/json")
    suspend fun getSystemTree(): HttpBean<List<SystemBean>>

    @GET("/navi/json")
    suspend fun getNavigation(): HttpBean<List<NavigationBoxBean>>

    @GET("project/tree/json")
    suspend fun getProjectTree(): HttpBean<List<ProjectTreeBean>>

    @GET("/project/list/{page}/json")
    suspend fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int) :HttpBean<ProjectBoxBean>
}