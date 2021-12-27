package com.zhao.wanandroid.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 *创建时间： 2021/12/17
 *编   写：  zjf
 *页面功能:  binding 顶级父类
 */
abstract class BaseBindingAdapter : RecyclerView.Adapter<BindingViewHolder<ViewDataBinding>>(){
    companion object {
        const val ADAPTER_ITEM = 0
        const val ADAPTER_HEADER = 1
        const val ADAPTER_FOOTER = 2
    }
}
