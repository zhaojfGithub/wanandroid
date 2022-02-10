package com.zhao.wanandroid.base.adapter.footer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BaseBindingAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.ItemFooterBinding

/**
 *创建时间： 2022/2/9
 *编   写：  zjf
 *页面功能: 本身设想此类为给列表添加上拉继续加载，和数据完成后的显示，如果需要其他业务，可以继承重写
 */
open class BaseSimplenessFooterAdapter : BaseBindingAdapter() {

    private var viewSate: AppState.LoadingState = AppState.LoadingState.NORMAL

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ViewDataBinding> {
        return getItemBindingViewHolder<ItemFooterBinding>(parent, R.layout.item_footer)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
        if (holder.binding is ItemFooterBinding) {
            holder.binding.data = viewSate
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    protected fun <VB : ViewDataBinding> getItemBindingViewHolder(parent: ViewGroup, @LayoutRes layout: Int): BindingViewHolder<ViewDataBinding> {
        val headerView: View = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        val binding: VB = DataBindingUtil.bind(headerView)!!
        return BindingViewHolder(binding)
    }

    fun updateViewState(state: AppState.LoadingState = AppState.LoadingState.NORMAL) {
        if (viewSate != state) {
            viewSate = state
        }
        notifyItemChanged(0)
    }
}