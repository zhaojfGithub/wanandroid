package com.zhao.wanandroid.ui.common

import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.databinding.BaseRecyclerBinding
import com.zhao.wanandroid.databinding.FragmentIntegralBinding

/**
 *创建时间： 2022/2/11
 *编   写：  zjf
 *页面功能:
 */
class IntegralFragment : BaseVmFragment<CommonViewModel,FragmentIntegralBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = IntegralFragment()
    }

    override fun getModelClass(): Class<CommonViewModel> {
        return CommonViewModel::class.java
    }

    override fun observer() {

    }

    override fun initData() {

    }

    override fun initView() {
        (activity as CommonActivity).setSupportActionBar(binding.toolbar)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_integral
    }
}