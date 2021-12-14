package com.zhao.wanandroid.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 *创建时间： 2021/12/14
 *编   写：  zjf
 *页面功能:  通用adapter
 */
abstract class BaseRecyclerAdapter<VB : ViewDataBinding, T : Any> : RecyclerView.Adapter<BindingViewHolder<VB>>() {

    protected val list: List<T> by lazy { ArrayList() }
    protected var onClick: ((Int, T) -> Unit)? = null
    protected var onLongClick: ((Int, T) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<VB> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId(), parent, false)
        val binder: VB = DataBindingUtil.bind(view)!!
        return BindingViewHolder(binder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @LayoutRes
    protected abstract fun layoutId(): Int

    fun addItem(bean: T) {
        (list as ArrayList).add(bean)
        notifyItemInserted(list.indexOf(bean))
    }

    fun addItems(list: List<T>) {
        val startIndex = if (list.isEmpty()) {
            0
        } else {
            list.size - 1
        }
        (list as ArrayList).addAll(list)
        notifyItemRangeInserted(startIndex, list.size)
    }

    fun removeItem(bean: T) {
        val index = list.indexOf(bean)
        if (index == -1) return
        (list as ArrayList).removeAt(index)
        notifyItemRemoved(index)
    }

    fun removeItems(list: List<T>) {
        if (this.list.isEmpty() || list.isEmpty()) return
        val index = list.indexOf(list[0])
        (this.list as ArrayList).removeAll(list.toSet())
        notifyItemRangeRemoved(index, list.size)
    }

    fun notifyAllItem(list: List<T>) {
        if (this.list.isNotEmpty()) {
            (this.list as ArrayList).clear()
            notifyItemRangeRemoved(0, this.list.size)
        }
        (this.list as ArrayList).addAll(list)
        notifyItemRangeInserted(0, this.list.size)
    }

    fun setItemOnClick(onClick: (Int, T) -> Unit) {
        this.onClick = onClick
    }

    fun setItemOnLongClick(onLongClick: (Int, T) -> Unit) {
        this.onLongClick = onLongClick
    }
}

