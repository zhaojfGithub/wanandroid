package com.zhao.wanandroid.ui.main.fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.indicator.CircleIndicator
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BindingViewHolder
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.bean.BannerBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.BaseBannerBinding
import com.zhao.wanandroid.databinding.ItemHomeBinding
import com.zhao.wanandroid.utils.LogUtils

/**
 *创建时间： 2021/12/14
 *编   写：  zjf
 *页面功能:
 */
class HomeAdapter : RecyclerView.Adapter<BindingViewHolder<ViewDataBinding>>() {

    private val articleItemList: List<ArticleItemBean> by lazy { ArrayList() }
    private val bannerList: List<BannerBean> by lazy { ArrayList() }
    private val bannerAdapter: HomeBannerAdapter by lazy { HomeBannerAdapter(arrayListOf()) }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return AppState.Adapter.ADAPTER_HEADER
        }
        return AppState.Adapter.ADAPTER_MIDDLE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ViewDataBinding> {
        return if (viewType == AppState.Adapter.ADAPTER_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.base_banner, parent, false)
            val binder: BaseBannerBinding = DataBindingUtil.bind(view)!!
            binder.lifecycleOwner = parent.findViewTreeLifecycleOwner()
            BindingViewHolder(binder)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
            val binder: ItemHomeBinding = DataBindingUtil.bind(view)!!
            BindingViewHolder(binder)
        }
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
        when (holder.binding) {
            is BaseBannerBinding -> {
                holder.binding.banner.addBannerLifecycleObserver(holder.binding.lifecycleOwner)
                holder.binding.banner.setAdapter(bannerAdapter)
                holder.binding.banner.indicator = CircleIndicator(holder.binding.banner.context)
            }
            is ItemHomeBinding -> {
                holder.binding.data = articleItemList[position - 1]
            }
            else -> {
                return
            }
        }
    }

    override fun getItemCount(): Int {
        return articleItemList.size + 1
    }

    fun changedBanner(data: List<BannerBean>) {
        if (this.bannerList.isNotEmpty()) {
            (this.bannerList as ArrayList).clear()
        }
        (this.bannerList as ArrayList).addAll(data)
        notifyItemChanged(0)
        LogUtils.e("设置banner")
    }

    fun refreshArticle(data: List<ArticleItemBean>) {
        if (this.articleItemList.isNotEmpty()) {
            (this.articleItemList as ArrayList).clear()
        }
        (this.articleItemList as ArrayList).addAll(data)
        notifyItemChanged(0, articleItemList.size)
        LogUtils.e("设置全部数据")
    }

    fun addAllArticle(data: List<ArticleItemBean>) {
        (this.articleItemList as ArrayList).addAll(data)
        notifyItemRangeInserted(itemCount, data.size)
        LogUtils.e("添加部分数据")
    }
}