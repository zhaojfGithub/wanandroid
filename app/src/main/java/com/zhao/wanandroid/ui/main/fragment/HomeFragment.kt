package com.zhao.wanandroid.ui.main.fragment

import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.databinding.FragmentHomeBinding
import com.zhao.wanandroid.ui.main.activity.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseVmFragment<MainViewModel,FragmentHomeBinding>() {


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun observer() {
        binding.data = viewModel
    }

}