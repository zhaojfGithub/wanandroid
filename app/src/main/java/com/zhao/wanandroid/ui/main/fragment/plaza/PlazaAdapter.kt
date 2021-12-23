package com.zhao.wanandroid.ui.main.fragment.plaza

import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BindingViewHolder
import com.zhao.wanandroid.base.adapter.BaseSimplifiedAdapter
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.databinding.ItemHomeBinding
import com.zhao.wanandroid.ui.main.fragment.home.HomeItemLabelAdapter

/**
 *创建时间： 2021/12/20
 *编   写：  zjf
 *页面功能:
 */
class PlazaAdapter : BaseSimplifiedAdapter<ItemHomeBinding, ArticleItemBean>() {
    override fun onBindBindingHolder(holder: BindingViewHolder<ItemHomeBinding>, position: Int) {
        holder.binding.data = list[position]
        holder.binding.recyclerView.apply {
            if (layoutManager == null) {
                val linearLayoutManager = object : LinearLayoutManager(holder.itemView.context, HORIZONTAL, false) {
                    override fun canScrollHorizontally(): Boolean {
                        return false
                    }
                }
                layoutManager = linearLayoutManager
            }
            if (adapter == null) {
                adapter = HomeItemLabelAdapter(list[position])
            }
        }
    }

    override fun layoutId(): Int {
        return R.layout.item_home
    }
}