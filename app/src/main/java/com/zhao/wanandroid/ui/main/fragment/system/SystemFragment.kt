package com.zhao.wanandroid.ui.main.fragment.system

import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.databinding.FragmentSystemBinding
import com.zhao.wanandroid.ui.main.activity.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SystemFragment : BaseVmFragment<SystemViewModel, FragmentSystemBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = SystemFragment()
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun getModelClass(): Class<SystemViewModel> {
        return SystemViewModel::class.java
    }

    override fun observer() {
        binding.data = viewModel
    }

}