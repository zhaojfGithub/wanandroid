package com.zhao.wanandroid.ui.main.fragment.home

import com.youth.banner.indicator.CircleIndicator
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.base.adapter.body.BaseSimplenessAdapter
import com.zhao.wanandroid.bean.BannerBean
import com.zhao.wanandroid.databinding.BaseBannerBinding

/**
 *创建时间： 2022/2/9
 *编   写：  zjf
 *页面功能:
 */
class BannerHeaderAdapter : BaseSimplenessAdapter<BaseBannerBinding, BannerBean>() {

    private val bannerAdapter: HomeBannerAdapter by lazy { HomeBannerAdapter(arrayListOf()) }

    override fun layoutId(): Int {
        return R.layout.base_banner
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<BaseBannerBinding>, position: Int) {
        if (holder.binding.banner.adapter == null) {
            holder.binding.banner.addBannerLifecycleObserver(holder.binding.lifecycleOwner)
            holder.binding.banner.setAdapter(bannerAdapter)
            holder.binding.banner.indicator = CircleIndicator(holder.binding.banner.context)
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun changedBanner(data: List<BannerBean>) {
        if (list.isNotEmpty()) {
            (list as ArrayList).clear()
        }
        (list as ArrayList).addAll(data)
        bannerAdapter.setDatas(list)
    }
}