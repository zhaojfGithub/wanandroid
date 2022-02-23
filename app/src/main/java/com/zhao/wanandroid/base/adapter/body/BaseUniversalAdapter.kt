package com.zhao.wanandroid.base.adapter.body

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zhao.wanandroid.base.adapter.BaseBindingAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.utils.LogUtils

/**
 *创建时间： 2021/12/17
 *编   写：  zjf
 *页面功能: 二级类，预想的可以应对单实体类型的所有情况
 */
abstract class BaseUniversalAdapter<T : Any> : BaseBindingAdapter(), AdapterInterface<T> {

    protected val list: MutableList<T> by lazy { ArrayList() }
    protected var onClick: ((Int, T) -> Unit)? = null
    protected var onLongClick: ((Int, T) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ViewDataBinding> {
        return onCreateBindingViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
        onBindItemViewHolder(holder, position)
    }

    abstract fun onCreateBindingViewHolder(parent: ViewGroup): BindingViewHolder<ViewDataBinding>

    abstract fun onBindItemViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int)


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onItemClick(onClick: (Int, T) -> Unit) {
        this.onClick = onClick
    }

    override fun onItemLongClick(onLongClick: (Int, T) -> Unit) {
        this.onLongClick = onLongClick
    }

    override fun refreshItem(data: T) {
        val index = list.indexOf(data)
        if (index == -1) return
        notifyItemChanged(index)
    }

    /**
     * 我寻思刷新全部的话，如果新的数量相同，是不是就替换数据刷新就可以了
     * 如果数据不同的话先添加或者减少再刷新会不会好点
     */
    override fun refreshAllItem(data: List<T>) {
        if (list.isNotEmpty()) {
            when {
                data.size < itemCount -> {
                    val number = itemCount - data.size
                    this.list.clear()
                    this.list.addAll(data)
                    notifyItemRangeRemoved(data.size, number)
                    notifyItemRangeChanged(0,data.size)
                }
                data.size > itemCount -> {
                    val number = data.size - itemCount
                    this.list.clear()
                    this.list.addAll(data)
                    notifyItemRangeChanged(0, itemCount)
                    notifyItemRangeInserted(itemCount, number)
                }
                else -> {
                    this.list.clear()
                    this.list.addAll(data)
                    notifyItemRangeChanged(0, this.list.size)
                }
            }
        }else{
            this.list.addAll(data)
            notifyItemRangeInserted(0, this.list.size - 1)
        }
    }

    override fun removeItemData(data: T) {
        val index = list.indexOf(data)
        if (index == -1) return
        notifyItemRemoved(index)
    }

    override fun removePartItemData(data: List<T>) {
        if (data.isEmpty() || this.list.isEmpty()) return
        val index = this.list.indexOf(data[0])
        notifyItemRangeRemoved(index, data.size)
        this.list.removeAll(data.toSet())
    }

    override fun addHeaderItemData(data: T) {
        this.list.add(0, data)
        notifyItemInserted(0)
    }

    override fun addFooterItemData(data: T) {
        this.list.add(data)
        notifyItemInserted(this.list.indexOf(data))
    }

    override fun addHeaderItemAllData(data: List<T>) {
        this.list.addAll(0, data)
        notifyItemRangeInserted(0, data.size)
    }

    override fun addFooterItemAllData(data: List<T>) {
        this.list.addAll(data)
        notifyItemRangeInserted(itemCount, data.size)
    }

    override fun <VB : ViewDataBinding> getItemBindingViewHolder(parent: ViewGroup, layout: Int): BindingViewHolder<ViewDataBinding> {
        val headerView: View = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        val binding: VB = DataBindingUtil.bind(headerView)!!
        return BindingViewHolder(binding)
    }
}