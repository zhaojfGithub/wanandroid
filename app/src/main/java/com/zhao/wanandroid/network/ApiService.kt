package com.zhao.wanandroid.network

import com.zhao.wanandroid.bean.*
import retrofit2.http.*

/**
 *创建时间： 2021/9/3
 *编   写：  zjf
 *页面功能:
 */
@Suppress("SpellCheckingInspection")
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
    suspend fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): HttpBean<ProjectBoxBean>

    @GET("/hotkey/json")
    suspend fun getHotSearchBean(): HttpBean<List<HotSearchBean>>

    @POST("/article/query/{page}/json")
    suspend fun getSearchQuery(@Path("page") page: Int, @Query("k") k: String): HttpBean<ArticleBoxBean>

    @POST("/lg/collect/{id}/json")
    suspend fun insertCollect(@Path("id") id: Int): HttpBean<ArticleItemBean>

    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectList(@Path("page") page: Int): HttpBean<ArticleBoxBean>

    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun clearCollect(@Path("id") id: Int): HttpBean<ArticleItemBean>

    @POST("/lg/user_article/add/json")
    suspend fun articleShare(@Query("link") url: String, @Query("title") title: String): HttpBean<ArticleItemBean>

    @GET("/lg/coin/list/{page}/json")
    suspend fun getIntegralDetail(@Path("page") page: Int): HttpBean<IntegralBoxBean>

    @GET("/lg/coin/userinfo/json")
    suspend fun getUserIntegral(): HttpBean<UserIntegralBean>

    @GET("/article/list/{page}/json")
    suspend fun getSystemArticle(@Path("page") page: Int, @Query("cid") cid: Int): HttpBean<ArticleBoxBean>

    @POST("login/selectUser")
    suspend fun loading(@Body testBean: TestBean = TestBean()) : String

}