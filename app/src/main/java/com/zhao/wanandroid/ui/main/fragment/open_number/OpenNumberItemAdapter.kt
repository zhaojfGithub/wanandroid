package com.zhao.wanandroid.ui.main.fragment.open_number

import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.business.BaseItemLabelAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.base.adapter.body.BaseSimplenessAdapter
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.databinding.ItemHomeBinding

/**
 *创建时间： 2021/12/22
 *编   写：  zjf
 *页面功能:
 */
class OpenNumberItemAdapter : BaseSimplenessAdapter<ItemHomeBinding, ArticleItemBean>() {

    override fun layoutId(): Int {
        return R.layout.item_home
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemHomeBinding>, position: Int) {
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
            val strList: MutableList<String> = ArrayList()
            val data = list[position]
            data.tags?.takeIf { it.isNotEmpty() }?.forEach {
                (strList as ArrayList).add(it.name)
            }
            if (data.author != null && data.author.isNotEmpty()) {
                (strList as ArrayList).add(data.author)
            } else if (data.shareUser != null && data.shareUser.isNotEmpty()) {
                (strList as ArrayList).add(data.shareUser)
            }
            val itemLabelAdapter = BaseItemLabelAdapter()
            itemLabelAdapter.addHeaderItemAllData(strList)
            adapter = itemLabelAdapter
        }
        holder.itemView.setOnClickListener {
            onClick?.invoke(position,list[position])
        }
    }

}