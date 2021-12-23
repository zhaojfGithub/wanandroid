package com.zhao.wanandroid.ui.main.fragment.home

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.youth.banner.adapter.BannerAdapter
import com.zhao.wanandroid.R
import com.zhao.wanandroid.bean.BannerBean
import com.zhao.wanandroid.utils.LogUtils

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