package com.zhao.wanandroid.ui.main.fragment.system

import com.google.android.flexbox.FlexboxLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.base.adapter.body.BaseSimplenessAdapter
import com.zhao.wanandroid.base.adapter.business.BaseItemLabelAdapter
import com.zhao.wanandroid.bean.SystemBean
import com.zhao.wanandroid.databinding.ItemSystemBinding

/**
 *创建时间： 2021/12/28
 *编   写：  zjf
 *页面功能:
 */
class SystemItemAdapter : BaseSimplenessAdapter<ItemSystemBinding, SystemBean>() {

    override fun layoutId(): Int {
        return R.layout.item_system
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemSystemBinding>, position: Int) {
        holder.binding.data = list[position].name
        holder.binding.recyclerView.apply {
            if (layoutManager == null) {
                layoutManager = FlexboxLayoutManager(holder.itemView.context)
            }
            val strList: MutableList<String> = ArrayList()
            val data = list[position]
            data.children.forEach {
                strList.add(it.name)
            }
            val itemLabelAdapter = BaseItemLabelAdapter()
            itemLabelAdapter.addHeaderItemAllData(strList)
            adapter = itemLabelAdapter
        }
        holder.itemView.setOnClickListener {
            onClick?.invoke(position, list[position])
        }
    }
}