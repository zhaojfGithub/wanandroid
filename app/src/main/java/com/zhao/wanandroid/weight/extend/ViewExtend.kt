package com.zhao.wanandroid.weight.extend

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhao.wanandroid.MyApplication

/**
 *创建时间： 2021/12/21
 *编   写：  zjf
 *页面功能:
 */

fun RecyclerView.isSlideBottom(endNumber: Int = 1, result: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (layoutManager !is LinearLayoutManager) return
            val linearLayoutManager = layoutManager as LinearLayoutManager
            val lastPosition = linearLayoutManager.findLastVisibleItemPosition()
            val visibleItemCount = linearLayoutManager.childCount
            val totalItemCount = linearLayoutManager.itemCount
            if (visibleItemCount > 0 && lastPosition == totalItemCount - 1 - endNumber) {
                result()
            }
        }
    })
}

/**
 * 扩展recyclerView方法，传入[position],使[position]在视图中居中显示
 * @param position 移动到中间的item下标
 * @param maxNumber 当前数据源的最大数量
 * 此种情况只适用于item宽度大体相同的情况
 */
fun RecyclerView?.smoothScrollToMiddlePosition(position: Int, maxNumber: Int) {
    if (this == null) return
    val firstItem = this.getChildLayoutPosition(this.getChildAt(0))
    val lastItem = this.getChildLayoutPosition(this.getChildAt(this.childCount - 1))
    val middleItem = (lastItem - firstItem) / 2
    if (position < firstItem) {
        if (position + middleItem < firstItem) {
            this.smoothScrollToPosition(position - middleItem)
        } else {
            this.smoothScrollToPosition(0)
        }
    } else if ((position > lastItem)) {
        if (position + middleItem < maxNumber - 1) {
            this.smoothScrollToPosition(position + middleItem)
        } else {
            this.smoothScrollToPosition(maxNumber - 1)
        }
    } else {
        when {
            position < middleItem -> {
                this.smoothScrollToPosition(0)
            }
            position > maxNumber - middleItem -> {
                this.smoothScrollToPosition(maxNumber - 1)
            }
            else -> {
                //计算position与中间item相差几个格子
                val firstPosition = firstItem + middleItem
                val positionItem = middleItem + (position - firstPosition)
                val middlePixel = this.getChildAt(middleItem).top
                val positionPixel = this.getChildAt(positionItem).top
                this.smoothScrollBy(0, positionPixel - middlePixel)
            }
        }
    }
}

/**

 */
fun RecyclerView?.smoothScrollToTopPosition(position: Int) {
    if (this == null || this.adapter == null) return
    var isBottomScroll = false
    val firstView = this.getChildAt(0)
    val lastView = this.getChildAt(this.childCount - 1)
    val firstIndex = this.getChildAdapterPosition(firstView)
    val lastIndex = this.getChildAdapterPosition(lastView)
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (isBottomScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {
                isBottomScroll = false
                smoothScrollToTopPosition(position)
                this@smoothScrollToTopPosition.removeOnScrollListener(this)
            }
        }
    })
    if (position < firstIndex) {
        this.smoothScrollToPosition(position)
    } else if (position > lastIndex) {
        this.smoothScrollToPosition(position)
        isBottomScroll = true
    } else {
        val movePosition = position - firstIndex
        if (movePosition >= 0 && movePosition < this.childCount) {
            val top = this.getChildAt(movePosition).top
            this.smoothScrollBy(0, top)
        }
    }
}

/**
 * 扩展recyclerView方法，移动到列表头部
 */
fun RecyclerView?.smoothScrollToHeaderPosition() {
    this?.smoothScrollToPosition(0)
}

/**
 * 扩展recyclerView方法，移动到列表底部
 */
fun RecyclerView?.smoothScrollToFooterPosition() {
    this?.adapter?.itemCount?.let { this.smoothScrollToPosition(it) }
}

fun showToast(msg: String) {
    Toast.makeText(MyApplication.getInstance(),msg,Toast.LENGTH_SHORT).show()
}