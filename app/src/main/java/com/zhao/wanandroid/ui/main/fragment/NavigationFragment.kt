package com.zhao.wanandroid.ui.main.fragment

import androidx.lifecycle.ViewModelProvider
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.databinding.FragmentNavigationBinding
import com.zhao.wanandroid.ui.main.activity.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *创建时间： 2021/12/10
 *编   写：  zjf
 *页面功能:
 */
@AndroidEntryPoint
class NavigationFragment : BaseVmFragment<MainViewModel, FragmentNavigationBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = NavigationFragment()
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_navigation
    }

    override fun getModelClass(): Class<MainViewModel> = MainViewModel::class.java


    override fun observer() {
        binding.data = viewModel
    }
}