package com.zhao.wanandroid.ui.setting

import android.view.MotionEvent
import androidx.core.content.ContextCompat
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.base.adapter.body.BaseSimplenessAdapter
import com.zhao.wanandroid.bean.SettingBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.ItemSettingBinding
import com.zhao.wanandroid.utils.DpUtil
import com.zhao.wanandroid.utils.ThemeColorUtil

/**
 *创建时间： 2022/2/21
 *编   写：  zjf
 *页面功能:
 */
class SettingAdapter : BaseSimplenessAdapter<ItemSettingBinding, SettingBean>() {
    override fun layoutId(): Int {
        return R.layout.item_setting
    }

    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemSettingBinding>, position: Int) {
        val data = list[position]
        holder.binding.data = data
        if (data.state == AppState.SettingItemState.NONE && data.details == null) {
            holder.binding.title.setTextColor(ThemeColorUtil.getThemeColor(holder.itemView.context, R.attr.colorPrimary))
            holder.binding.title.textSize = DpUtil.px2dp(holder.itemView.context, holder.binding.title.textSize - 3).toFloat()
        }
        if (data.state != AppState.SettingItemState.CHECK && data.details != null) {
            holder.itemView.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN ->{
                        v.setBackgroundColor(ContextCompat.getColor(v.context,R.color.gray))
                    }
                    MotionEvent.ACTION_UP ->{
                        v.setBackgroundColor(ContextCompat.getColor(v.context,R.color.white))
                    }
                    MotionEvent.ACTION_CANCEL ->{
                        v.setBackgroundColor(ContextCompat.getColor(v.context,R.color.white))
                    }
                }
                v.performClick()
                false
            }
            holder.itemView.setOnClickListener {
                onClick?.invoke(position, data)
            }
        } else {
            holder.binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                data.check = isChecked
                onClick?.invoke(position, data)
            }
        }
    }
}