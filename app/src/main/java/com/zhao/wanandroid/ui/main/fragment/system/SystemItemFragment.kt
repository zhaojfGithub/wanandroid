package com.zhao.wanandroid.ui.main.fragment.system

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.databinding.FragmentSystemItemBinding
import com.zhao.wanandroid.ui.main.fragment.open_number.OpenNumberItemFragment

/**
 *创建时间： 2021/12/28
 *编   写：  zjf
 *页面功能:
 */
class SystemItemFragment : BaseVmFragment<SystemViewModel, FragmentSystemItemBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return OpenNumberItemFragment()
        }
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun observer() {
        super.observer()

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system_item
    }

    override fun getModelClass(): Class<SystemViewModel> {
        return SystemViewModel::class.java
    }
}