package com.zhao.wanandroid.ui.main.fragment.system

import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BaseItemLabelAdapter
import com.zhao.wanandroid.base.adapter.BaseSimplifiedAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.bean.SystemBean
import com.zhao.wanandroid.databinding.ItemSystemBinding

/**
 *创建时间： 2021/12/28
 *编   写：  zjf
 *页面功能:
 */
class SystemItemAdapter : BaseSimplifiedAdapter<ItemSystemBinding, SystemBean>() {

    override fun layoutId(): Int {
        return R.layout.item_system
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemSystemBinding>, position: Int) {
        holder.binding.data = list[position].name
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
            val data = list[position - 1]
            data.children.forEach {
                strList.add(it.name)
            }
            val itemLabelAdapter = BaseItemLabelAdapter()
            itemLabelAdapter.addHeaderItemAllData(strList)
            adapter = itemLabelAdapter
        }
        onClick?.also {
            holder.itemView.setOnClickListener {
                it(0, list[position])
            }
        }

    }
}