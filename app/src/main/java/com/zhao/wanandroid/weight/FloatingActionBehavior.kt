package com.zhao.wanandroid.weight

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.utils.LogUtils

/**
 *创建时间： 2021/12/21
 *编   写：  zjf
 *页面功能:
 */
class FloatingActionBehavior(context: Context, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {

    private var outAnimator: ObjectAnimator? = null
    private var inAnimator: ObjectAnimator? = null

    private var width: Float = 0F
    private val windowWidth: Float = MyApplication.Instance().resources.displayMetrics.widthPixels.toFloat()

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {

    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        if (dyConsumed > 0) { // 上滑隐藏
            if (outAnimator == null) {
                width = windowWidth - child.left
                outAnimator = ObjectAnimator.ofFloat(child, "translationX", 0F, child.right.toFloat())
                outAnimator?.duration = 200
            }
            if (!outAnimator!!.isRunning && child.translationX <= 0) {
                outAnimator!!.start()
            }
        } else if (dyConsumed < 0) {  // 下滑显示
            if (inAnimator == null) {
                inAnimator = ObjectAnimator.ofFloat(child, "translationX", width, 0f)
                inAnimator?.duration = 200
            }
            if (!inAnimator!!.isRunning && child.translationX >= child.height) {
                inAnimator!!.start()
            }
        }
    }
}