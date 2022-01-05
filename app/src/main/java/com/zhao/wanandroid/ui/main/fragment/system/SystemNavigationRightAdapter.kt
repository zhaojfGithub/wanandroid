package com.zhao.wanandroid.ui.main.fragment.system

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.google.android.flexbox.FlexboxLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BaseSimplifiedAdapter
import com.zhao.wanandroid.base.adapter.BaseUniversalAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.bean.NavigationBoxBean
import com.zhao.wanandroid.bean.NavigationItemBean
import com.zhao.wanandroid.databinding.ItemNavigationBinding
import com.zhao.wanandroid.databinding.ItemRecyclerBinding

/**
 *创建时间： 2022/1/4
 *编   写：  zjf
 *页面功能:  本来想使用ConcatAdapter来做的，后来仔细看过文档之后发现并不适用于这种写法，他的诞生应该是适用于123形式，而不是121212形式，不过列表头·列表尾，倒是很适用于这种情况
 */
class SystemNavigationRightAdapter : BaseSimplifiedAdapter<ItemRecyclerBinding, NavigationBoxBean>() {

    override fun layoutId(): Int {
        return R.layout.item_recycler
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemRecyclerBinding>, position: Int) {
        holder.binding.data = list[position].name
        holder.binding.recyclerView.apply {
            if (layoutManager == null) {
                layoutManager = FlexboxLayoutManager(holder.itemView.context)
                val itemAdapter = NavigationItemAdapter()
                itemAdapter.refreshAllItem(list[position].articles)
                adapter = itemAdapter
            }
        }
    }
}