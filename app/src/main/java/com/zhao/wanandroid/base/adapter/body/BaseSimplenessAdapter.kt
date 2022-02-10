package com.zhao.wanandroid.base.adapter.body

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zhao.wanandroid.base.adapter.BindingViewHolder


/**
 *创建时间： 2021/12/14
 *编   写：  zjf
 *页面功能:  简化通用adapter，应用于单布局但数据源
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseSimplenessAdapter<VB : ViewDataBinding, T : Any> : BaseUniversalAdapter<T>() {

    @LayoutRes
    protected abstract fun layoutId(): Int

    override fun onCreateBindingViewHolder(parent: ViewGroup): BindingViewHolder<ViewDataBinding> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId(), parent, false)
        val binder: VB = DataBindingUtil.bind(view)!!
        return BindingViewHolder(binder)
    }

    override fun onBindItemViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
        onBindBindingViewHolder(holder as BindingViewHolder<VB>,position)
    }

    abstract fun onBindBindingViewHolder(holder: BindingViewHolder<VB>, position: Int)

}

