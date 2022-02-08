package com.zhao.wanandroid.ui.search

import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BaseSimplifiedAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.databinding.ItemHistorySearchBinding

/**
 *创建时间： 2022/2/8
 *编   写：  zjf
 *页面功能:
 */
class HistorySearchAdapter : BaseSimplifiedAdapter<ItemHistorySearchBinding,String>() {
    override fun layoutId(): Int {
        return R.layout.item_history_search
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemHistorySearchBinding>, position: Int) {
        holder.binding.data = list[position]
        if (onClick != null){
            holder.itemView.setOnClickListener {
                onClick?.invoke(1,list[position])
            }
            holder.binding.ivClear.setOnClickListener {
                onClick?.invoke(2,list[position])
            }
        }
    }

    fun updateItemIndex(text:String){
        val index = list.indexOf(text)
        if (index == -1){
            addHeaderItemData(text)
        }
        if (index != 0){
            //不为0移动到最上面
            notifyItemMoved(index,0)
        }
    }
}