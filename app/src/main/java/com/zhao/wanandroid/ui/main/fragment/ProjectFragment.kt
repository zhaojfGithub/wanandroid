package com.zhao.wanandroid.ui.main.fragment

import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.databinding.FragmentProjectBinding
import com.zhao.wanandroid.ui.main.activity.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectFragment : BaseVmFragment<MainViewModel, FragmentProjectBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = ProjectFragment()
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun getModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun observer() {
        binding.data = viewModel
    }

}