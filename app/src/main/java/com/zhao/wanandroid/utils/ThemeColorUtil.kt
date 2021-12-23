package com.zhao.wanandroid.utils

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import com.zhao.wanandroid.MyApplication


/**
 *创建时间： 2021/12/16
 *编   写：  zjf
 *页面功能:
 */
object ThemeColorUtil {

    /**
     * 离谱 同一个theme 用Application和普通的context取出来的颜色居然不一样
     */
    fun getThemeColor(context: Context , @AttrRes attr: Int): Int {
        val typeValue = TypedValue()
        val theme = context.theme
        theme.resolveAttribute(attr, typeValue, true)
        return typeValue.data
    }
}