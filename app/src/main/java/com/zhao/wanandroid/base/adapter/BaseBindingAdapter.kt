package com.zhao.wanandroid.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.zhao.wanandroid.base.BindingViewHolder

/**
 *创建时间： 2021/12/17
 *编   写：  zjf
 *页面功能:  binding 顶级父类
 */
abstract class BaseBindingAdapter : RecyclerView.Adapter<BindingViewHolder<ViewDataBinding>>()
