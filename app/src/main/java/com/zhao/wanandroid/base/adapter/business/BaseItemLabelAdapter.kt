package com.zhao.wanandroid.base.adapter.business

import androidx.core.content.ContextCompat
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.body.BaseSimplenessAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.databinding.ItemLabelBinding
import com.zhao.wanandroid.utils.ThemeColorUtil

/**
 *创建时间： 2021/12/29
 *编   写：  zjf
 *页面功能: 项目中有很多标签，认为可以做成一个标签通用的adapter
 */
open class BaseItemLabelAdapter : BaseSimplenessAdapter<ItemLabelBinding, String>(){
    override fun layoutId(): Int {
        return R.layout.item_label
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemLabelBinding>, position: Int) {
        holder.binding.data = list[position]
        when (list[position]) {
            "本站发布" -> {
                holder.binding.tvItemLabel.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.shape_frame_primary)
                holder.binding.tvItemLabel.setTextColor(ThemeColorUtil.getThemeColor(holder.itemView.context, R.attr.colorPrimary))
            }
            "公众号" -> {
                holder.binding.tvItemLabel.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.shape_frame_primary)
                holder.binding.tvItemLabel.setTextColor(ThemeColorUtil.getThemeColor(holder.itemView.context, R.attr.colorPrimary))

            }
            "问答" -> {
                holder.binding.tvItemLabel.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.shape_frame_primary)
                holder.binding.tvItemLabel.setTextColor(ThemeColorUtil.getThemeColor(holder.itemView.context, R.attr.colorPrimary))

            }
            "新" -> {
                holder.binding.tvItemLabel.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.shape_frame_on_primary)
                holder.binding.tvItemLabel.setTextColor(ThemeColorUtil.getThemeColor(holder.itemView.context, R.attr.colorOnPrimary))
            }
            "置顶" -> {
                holder.binding.tvItemLabel.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.shape_frame_on_primary)
                holder.binding.tvItemLabel.setTextColor(ThemeColorUtil.getThemeColor(holder.itemView.context, R.attr.colorOnPrimary))
            }
            else -> {

            }
        }
    }
}