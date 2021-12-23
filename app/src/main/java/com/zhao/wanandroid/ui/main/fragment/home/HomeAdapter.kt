package com.zhao.wanandroid.ui.main.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.indicator.CircleIndicator
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BindingViewHolder
import com.zhao.wanandroid.base.adapter.BaseUniversalAdapter
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.bean.BannerBean
import com.zhao.wanandroid.common.AppState
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


    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return AppState.Adapter.ADAPTER_HEADER
        }
        return AppState.Adapter.ADAPTER_MIDDLE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ViewDataBinding> {
        return if (viewType == AppState.Adapter.ADAPTER_HEADER) {
            val binder: BaseBannerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.base_banner, parent, false)
            binder.lifecycleOwner = parent.findViewTreeLifecycleOwner()
            BindingViewHolder(binder)
        } else {
            val binder: ItemHomeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_home, parent, false)
            BindingViewHolder(binder)
        }
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
        when (holder.binding) {
            is BaseBannerBinding -> {
                if (holder.binding.banner.adapter == null) {
                    holder.binding.banner.addBannerLifecycleObserver(holder.binding.lifecycleOwner)
                    holder.binding.banner.setAdapter(bannerAdapter)
                    holder.binding.banner.indicator = CircleIndicator(holder.binding.banner.context)
                }
            }
            is ItemHomeBinding -> {
                holder.binding.data = list[position - 1]
                holder.binding.recyclerView.apply {
                    if (layoutManager == null) {
                        val linearLayoutManager = object : LinearLayoutManager(holder.itemView.context, HORIZONTAL, false) {
                            override fun canScrollHorizontally(): Boolean {
                                return false
                            }
                        }
                        layoutManager = linearLayoutManager
                    }
                    if (adapter == null) {
                        adapter = HomeItemLabelAdapter(list[position - 1])
                    }
                }
            }
            else -> {
                return
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    fun changedBanner(data: List<BannerBean>) {
        if (this.bannerList.isNotEmpty()) {
            (this.bannerList as ArrayList).clear()
        }
        (this.bannerList as ArrayList).addAll(data)
        bannerAdapter.setDatas(this.bannerList)
    }
}