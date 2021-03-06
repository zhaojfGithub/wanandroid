package com.zhao.wanandroid.ui.main.fragment.project

import com.bumptech.glide.Glide
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.body.BaseSimplenessAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.bean.ProjectItemBean
import com.zhao.wanandroid.databinding.ItemProjectBinding

/**
 *创建时间： 2022/1/5
 *编   写：  zjf
 *页面功能:
 */
class ProjectItemAdapter : BaseSimplenessAdapter<ItemProjectBinding, ProjectItemBean>() {

    override fun layoutId(): Int {
        return R.layout.item_project
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemProjectBinding>, position: Int) {
        holder.binding.data = list[position]
        Glide.with(holder.itemView).load(list[position].envelopePic).into(holder.binding.ivImage)
        if (list[position].collect) {
            holder.binding.ivIsCollect.setImageResource(R.drawable.ic_favorite)
        } else {
            holder.binding.ivIsCollect.setImageResource(R.drawable.ic_favorite_false)
        }
        holder.itemView.setOnClickListener {
            onClick?.invoke(0,list[position])
        }
        holder.binding.ivIsCollect.setOnClickListener {
            list[position].collect = !list[position].collect
            onClick?.invoke(1,list[position])
        }
    }
}