package com.zhao.wanandroid.ui.main.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.youth.banner.indicator.CircleIndicator
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BaseItemLabelAdapter
import com.zhao.wanandroid.base.adapter.BaseUniversalAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.bean.BannerBean
import com.zhao.wanandroid.databinding.BaseBannerBinding
import com.zhao.wanandroid.databinding.ItemHomeBinding

/**
 *创建时间： 2021/12/14
 *编   写：  zjf
 *页面功能:
 */
class HomeAdapter : BaseUniversalAdapter<ArticleItemBean>() {

    private val bannerList: MutableList<BannerBean> by lazy { ArrayList() }
    private val bannerAdapter: HomeBannerAdapter by lazy { HomeBannerAdapter(arrayListOf()) }

    init {
        addHeaderView()
    }

    override fun onCreateBindingViewHolder(parent: ViewGroup): BindingViewHolder<ViewDataBinding> {
        val binder: ItemHomeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_home, parent, false)
        return BindingViewHolder(binder)
    }

    override fun onBindItemViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
        when (holder.binding) {
            is BaseBannerBinding -> {
                if (holder.binding.banner.adapter == null) {
                    holder.binding.banner.addBannerLifecycleObserver(holder.binding.lifecycleOwner)
                    holder.binding.banner.setAdapter(bannerAdapter)
                    holder.binding.banner.indicator = CircleIndicator(holder.binding.banner.context)
                }
            }
            is ItemHomeBinding -> {
                val data = list[position - 1]
                holder.binding.data = data
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
                    data.tags?.takeIf { it.isNotEmpty() }?.forEach {
                        (strList as ArrayList).add(it.name)
                    }
                    if (data.author != null && data.author.isNotEmpty()) {
                        (strList as ArrayList).add(data.author)
                    } else if (data.shareUser != null && data.shareUser.isNotEmpty()) {
                        (strList as ArrayList).add(data.shareUser)
                    }
                    val itemLabelAdapter = BaseItemLabelAdapter()
                    itemLabelAdapter.addHeaderItemAllData(strList)
                    adapter = itemLabelAdapter
                }
                onClick?.also {
                    holder.itemView.setOnClickListener { _->
                        it(0, data)
                    }
                }

            }
            else -> {
                return
            }
        }
    }

    override fun getHeaderBindingViewHolder(parent: ViewGroup): BindingViewHolder<ViewDataBinding> {
        return getItemBindingViewHolder<BaseBannerBinding>(parent, R.layout.base_banner)
    }

    fun changedBanner(data: List<BannerBean>) {
        if (this.bannerList.isNotEmpty()) {
            (this.bannerList as ArrayList).clear()
        }
        (this.bannerList as ArrayList).addAll(data)
        bannerAdapter.setDatas(this.bannerList)
    }
}