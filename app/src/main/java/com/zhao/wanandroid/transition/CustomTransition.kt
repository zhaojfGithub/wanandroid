package com.zhao.wanandroid.transition

import android.animation.Animator
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionValues

/**
 *创建时间： 2021/9/24
 *编   写：  zjf
 *页面功能:
 */
class CustomTransition : Transition() {
    /**
     * 是否需要初始
     */
    override fun captureStartValues(transitionValues: TransitionValues) {

    }

    override fun captureEndValues(transitionValues: TransitionValues) {

    }

    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
        return super.createAnimator(sceneRoot, startValues, endValues)
    }
}