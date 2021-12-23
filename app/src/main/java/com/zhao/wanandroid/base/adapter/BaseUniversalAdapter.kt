package com.zhao.wanandroid.base.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.zhao.wanandroid.base.BindingViewHolder

/**
 *创建时间： 2021/12/17
 *编   写：  zjf
 *页面功能: 二级类，预想的可以应对单实体类型的所有情况
 */
abstract class BaseUniversalAdapter<T : Any> : BaseBindingAdapter(), AdapterInterface<T> {

    protected val list: MutableList<T> by lazy { ArrayList() }
    protected var onClick: ((Int, T) -> Unit)? = null
    protected var onLongClick: ((Int, T) -> Unit)? = null

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

    override fun refreshAllItem(data: List<T>) {
        notifyItemRangeRemoved(0, this.list.size)
        this.list.clear()
        this.list.addAll(data)
        notifyItemRangeChanged(0, this.list.size)

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
        notifyItemRangeChanged(0, data.size)
    }

    override fun addFooterItemAllData(data: List<T>) {
        this.list.addAll(data)
        notifyItemRangeChanged(itemCount, data.size)
    }

}