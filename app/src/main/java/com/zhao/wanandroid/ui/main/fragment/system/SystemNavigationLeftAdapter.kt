package com.zhao.wanandroid.ui.main.fragment.system

import androidx.core.content.ContextCompat
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.adapter.body.BaseSimplenessAdapter
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.bean.NavigationBoxBean
import com.zhao.wanandroid.databinding.ItemNavigationBinding
import com.zhao.wanandroid.utils.ThemeColorUtil


/**
 *创建时间： 2021/12/30
 *编   写：  zjf
 *页面功能:
 */
class SystemNavigationLeftAdapter : BaseSimplenessAdapter<ItemNavigationBinding, NavigationBoxBean>() {

    /**
     * 当前选中的item
     */
    private var selectBean: NavigationBoxBean? = null

    override fun layoutId(): Int {
        return R.layout.item_navigation
    }

    /**
     * 不知道为啥找不到R文件，只能写在这里咯
     */
    override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemNavigationBinding>, position: Int) {
        holder.binding.data = list[position]
        if (list[position].isSelect) {
            holder.itemView.setBackgroundColor(ThemeColorUtil.getThemeColor(MyApplication.getInstance(), R.attr.colorPrimary))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(MyApplication.getInstance(), R.color.gray))
        }
        holder.itemView.setOnClickListener {
            onClick?.also {
                if (selectBean != null) {
                    selectBean!!.isSelect = false
                    refreshItem(selectBean!!)
                }
                it(position, list[position])
                selectBean = list[position]
            }
        }

    }
}