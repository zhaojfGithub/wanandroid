package com.zhao.wanandroid.bean

import com.google.gson.annotations.SerializedName

/**
 *创建时间： 2021/12/14
 *编   写：  zjf
 *页面功能:
 */
data class BannerBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)

data class ArticleBoxBean(
    val curPage: Int,
    @SerializedName("datas")
    val data: List<ArticleItemBean>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class ArticleItemBean(
    val apkLink: String,
    val audit: Int,
    val author: String? = null,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    var collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val host: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String? = null,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<ArticleLabelBean>? = null,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int,
)

data class ArticleLabelBean(
    val name: String,
    val url: String? = null
)