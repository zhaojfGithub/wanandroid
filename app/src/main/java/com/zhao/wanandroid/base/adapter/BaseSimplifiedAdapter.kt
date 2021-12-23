package com.zhao.wanandroid.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zhao.wanandroid.base.BindingViewHolder


/**
 *创建时间： 2021/12/14
 *编   写：  zjf
 *页面功能:  简化通用adapter，应用于单布局但数据源
 */
abstract class BaseSimplifiedAdapter<VB : ViewDataBinding, T : Any> : BaseUniversalAdapter<T>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ViewDataBinding> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId(), parent, false)
        val binder: VB = DataBindingUtil.bind(view)!!
        return BindingViewHolder(binder)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
        onBindBindingHolder(holder as BindingViewHolder<VB>, position)
    }

    abstract fun onBindBindingHolder(holder: BindingViewHolder<VB>, position: Int)

    @LayoutRes
    protected abstract fun layoutId(): Int

}

