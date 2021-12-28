package com.zhao.wanandroid.ui.main.fragment.system

import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BaseSimplifiedAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.bean.SystemBean
import com.zhao.wanandroid.databinding.ItemSystemBinding

/**
 *创建时间： 2021/12/28
 *编   写：  zjf
 *页面功能:
 */
class SystemItemAdapter : BaseSimplifiedAdapter<ItemSystemBinding,SystemBean>() {



    override fun layoutId(): Int {
        return R.layout.item_system
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemSystemBinding>, position: Int) {
        holder.binding.data = list[position].name

    }
}