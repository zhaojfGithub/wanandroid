package com.zhao.wanandroid.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 *创建时间： 2021/12/14
 *编   写：  zjf
 *页面功能:  dataBinding绑定viewHolder
 */
class BindingViewHolder<VB : ViewDataBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)