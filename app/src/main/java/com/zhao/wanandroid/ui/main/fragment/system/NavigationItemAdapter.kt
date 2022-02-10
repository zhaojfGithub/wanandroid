package com.zhao.wanandroid.ui.main.fragment.system

import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.body.BaseSimplenessAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.bean.NavigationItemBean
import com.zhao.wanandroid.databinding.ItemNavigationTwoBinding
import java.util.*

/**
 *创建时间： 2022/1/4
 *编   写：  zjf
 *页面功能:
 */
class NavigationItemAdapter : BaseSimplenessAdapter<ItemNavigationTwoBinding, NavigationItemBean>() {

    private val colorBox: Array<Int> by lazy {
        arrayOf(R.color.blue_200, R.color.blue_500, R.color.orange_200, R.color.orange_500, R.color.black, R.color.gray, android.R.color.holo_red_dark)
    }
    private val random = Random()

    override fun layoutId(): Int {
        return R.layout.item_navigation_two
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemNavigationTwoBinding>, position: Int) {
        holder.binding.data = list[position].title
        holder.binding.tvName.setTextColor(colorBox[random.nextInt(colorBox.size - 1)])
    }
}