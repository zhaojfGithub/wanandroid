package com.zhao.wanandroid.ui.search

import androidx.core.content.ContextCompat
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BaseSimplifiedAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.databinding.ItemNavigationTwoBinding


/**
 *创建时间： 2022/2/8
 *编   写：  zjf
 *页面功能:
 */
class HotSearchAdapter : BaseSimplifiedAdapter<ItemNavigationTwoBinding, String>() {

    private val colorBox: Array<Int> by lazy {
        arrayOf(R.color.blue_200, R.color.blue_500, R.color.orange_200, R.color.orange_500, R.color.black,R.color.text_initial)
    }

    override fun layoutId(): Int {
        return R.layout.item_navigation_two
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemNavigationTwoBinding>, position: Int) {
        holder.binding.data = list[position]
        val index = (colorBox.indices).random()
        holder.binding.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context, colorBox[index]))
        onClick?.also {
            holder.itemView.setOnClickListener {
                onClick?.invoke(-1,list[position])
            }
        }
    }
}