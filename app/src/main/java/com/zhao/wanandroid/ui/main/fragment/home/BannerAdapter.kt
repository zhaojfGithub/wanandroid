package com.zhao.wanandroid.ui.main.fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youth.banner.adapter.BannerAdapter
import com.zhao.wanandroid.R
import com.zhao.wanandroid.bean.BannerBean

/**
 *创建时间： 2021/12/15
 *编   写：  zjf
 *页面功能:
 */
class HomeBannerAdapter(data: List<BannerBean>) : BannerAdapter<BannerBean, HomeBannerAdapter.ViewHolder>(data) {

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return ViewHolder(view)
    }

    override fun onBindView(holder: ViewHolder, data: BannerBean, position: Int, size: Int) {
        Glide.with(holder.itemView.context).load(data.imagePath)
            .into(holder.ivBannerImg)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivBannerImg: ImageView = itemView.findViewById(R.id.ivBannerImg)
    }
}