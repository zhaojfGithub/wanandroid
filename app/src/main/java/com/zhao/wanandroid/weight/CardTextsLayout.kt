package com.zhao.wanandroid.weight

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

/**
 *创建时间： 2022/1/4
 *编   写：  zjf
 *页面功能:
 */
class CardTextsLayout : ViewGroup {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 计算当前view以及子view的位置
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

    /**
     *
     */
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context,attrs)
    }

    /**
     * 计算当前view以及子view的位置
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * 视图的绘制工作
     */
    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }
}