package com.zhao.wanandroid.extend

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhao.wanandroid.utils.LogUtils

/**
 *创建时间： 2021/12/16
 *编   写：  zjf
 *页面功能:
 */
fun RecyclerView.isSlideBottom(result: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (layoutManager !is LinearLayoutManager) return
            val linearLayoutManager = layoutManager as LinearLayoutManager
            val lastPosition = linearLayoutManager.findLastVisibleItemPosition()
            val visibleItemCount = linearLayoutManager.childCount
            val totalItemCount = linearLayoutManager.itemCount
            if (visibleItemCount > 0 && lastPosition == totalItemCount - 1) {
                result()
            }
        }
    })
}