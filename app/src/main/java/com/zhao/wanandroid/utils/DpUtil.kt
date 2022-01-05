package com.zhao.wanandroid.utils

import android.content.Context
import com.zhao.wanandroid.MyApplication

/**
 *创建时间： 2022/1/4
 *编   写：  zjf
 *页面功能:
 */
object DpUtil {
    fun dip2px(context: Context = MyApplication.Instance(), dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5F).toInt()
    }

    fun px2dp(context: Context = MyApplication.Instance(), px: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5F).toInt()
    }
}