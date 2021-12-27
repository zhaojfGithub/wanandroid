package com.zhao.wanandroid.ui.main.fragment.home

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BaseSimplifiedAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.databinding.ItemLabelBinding
import com.zhao.wanandroid.utils.ThemeColorUtil

/**
 *创建时间： 2021/12/16
 *编   写：  zjf
 *页面功能:
 */
class HomeItemLabelAdapter(data: ArticleItemBean) : BaseSimplifiedAdapter<ItemLabelBinding, String>() {

    init {
        data.tags?.takeIf { it.isNotEmpty() }?.forEach {
            (list as ArrayList).add(it.name)
        }
        if (data.author != null && data.author.isNotEmpty()) {
            (list as ArrayList).add(data.author)
        } else if (data.shareUser != null && data.shareUser.isNotEmpty()) {
            (list as ArrayList).add(data.shareUser)
        }
    }

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