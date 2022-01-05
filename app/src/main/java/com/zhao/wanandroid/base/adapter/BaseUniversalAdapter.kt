package com.zhao.wanandroid.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zhao.wanandroid.R
import com.zhao.wanandroid.databinding.IncludeItemEndBinding
import com.zhao.wanandroid.databinding.IncludeItemStartBinding
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

    /*头布局和尾布局*/
    private var isAddHeader: Boolean = false
    private var isAddFooter: Boolean = false

    protected var isShowHeader: Boolean = false
    protected var isShowFooter: Boolean = false

    override fun getItemViewType(position: Int): Int {
        if (isAddHeader && position == 0) {
            return ADAPTER_HEADER
        }
        if (isAddFooter) {
            if (!isAddHeader && position == list.size) {
                return ADAPTER_FOOTER
            }
            if (isAddHeader && position == list.size + 1) {
                return ADAPTER_FOOTER
            }
        }
        return ADAPTER_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ViewDataBinding> {
        when (viewType) {
            ADAPTER_HEADER -> {
                return getHeaderBindingViewHolder(parent)
            }
            ADAPTER_FOOTER -> {
                return getFooterBindingViewHolder(parent)
            }
            ADAPTER_ITEM -> {
                return onCreateBindingViewHolder(parent)
            }
        }
        return onCreateBindingViewHolder(parent)
    }

    /**
     * 可重写此方法，达到替换列表头的目的
     */
    protected open fun getHeaderBindingViewHolder(parent: ViewGroup): BindingViewHolder<ViewDataBinding> {
        return getItemBindingViewHolder<IncludeItemStartBinding>(parent, R.layout.include_item_start)
    }

    /**
     * 可重写此方法，达到替换列表尾的目的
     */
    protected open fun getFooterBindingViewHolder(parent: ViewGroup): BindingViewHolder<ViewDataBinding> {
        return getItemBindingViewHolder<IncludeItemEndBinding>(parent, R.layout.include_item_end)
    }


    override fun onBindViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
        when (holder.binding) {
            is IncludeItemStartBinding -> {
                holder.binding.data = isShowHeader
            }
            is IncludeItemEndBinding -> {
                holder.binding.data = isShowFooter
            }
            else -> {
                onBindItemViewHolder(holder, position)
            }
        }

    }

    abstract fun onCreateBindingViewHolder(parent: ViewGroup): BindingViewHolder<ViewDataBinding>

    abstract fun onBindItemViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int)


    override fun getItemCount(): Int {
        var size = list.size
        if (isAddHeader) size++
        if (isAddFooter) size++
        return size
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
        if (list.isNotEmpty()) {
            notifyItemRangeRemoved(0, this.list.size)
            this.list.clear()
        }
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

    override fun addHeaderView() {
        this.isAddHeader = true
    }

    override fun addFooterView() {
        this.isAddFooter = true
    }

    override fun showHeaderView() {
        this.isShowHeader = true
        notifyItemChanged(0)
    }

    override fun showFooterView() {
        this.isShowFooter = true
        notifyItemChanged(itemCount - 1)
    }

    override fun dismissHeaderView() {
        this.isShowHeader = false
        notifyItemChanged(0)
    }

    override fun dismissFooterView() {
        this.isShowFooter = false
        notifyItemChanged(itemCount - 1)
    }

    override fun <VB : ViewDataBinding> getItemBindingViewHolder(parent: ViewGroup, layout: Int): BindingViewHolder<ViewDataBinding> {
        val headerView: View = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        val binding: VB = DataBindingUtil.bind(headerView)!!
        return BindingViewHolder(binding)
    }
}