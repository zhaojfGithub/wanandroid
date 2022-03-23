package com.zhao.wanandroid.ui.common

import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.base.adapter.body.BaseSimplenessAdapter
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.databinding.ItemCollectBinding

/**
 *创建时间： 2022/2/23
 *编   写：  zjf
 *页面功能:
 */
class CollectAdapter : BaseSimplenessAdapter<ItemCollectBinding,ArticleItemBean>() {
    override fun layoutId(): Int {
        return R.layout.item_collect
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemCollectBinding>, position: Int) {
        holder.binding.data = list[position]
        holder.itemView.setOnClickListener {
            onClick?.invoke(0,list[position])
        }
        holder.binding.imageFilterView.setOnClickListener {
            list[position].collect = !list[position].collect
            onClick?.invoke(1,list[position])
        }
    }
}