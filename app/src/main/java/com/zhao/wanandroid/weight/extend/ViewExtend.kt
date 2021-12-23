package com.zhao.wanandroid.weight.extend

import androidx.recyclerview.widget.RecyclerView

/**
 *创建时间： 2021/12/21
 *编   写：  zjf
 *页面功能:
 */

/**
 * 扩展recyclerView方法，传入[position],使[position]在视图中居中显示
 * @param position 移动到中间的item下标
 * @param maxNumber 当前数据源的最大数量
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
 * 扩展recyclerView方法，移动到列表头部
 */
fun RecyclerView?.smoothScrollToHeaderPosition(){
    this?.smoothScrollToPosition(0)
}

/**
 * 扩展recyclerView方法，移动到列表底部
 */
fun RecyclerView?.smoothScrollToFooterPosition(){
    this?.adapter?.itemCount?.let { this.smoothScrollToPosition(it) }
}