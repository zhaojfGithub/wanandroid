package com.zhao.wanandroid.ui.main.fragment.home


import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.databinding.FragmentHomeBinding
import com.zhao.wanandroid.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseVmFragment<HomeViewModel, FragmentHomeBinding>() {


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun initData() {
        viewModel.aaa()
    }

    override fun initView() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun observer() {
        binding.data = viewModel
        viewModel.apply {
            bbb.observe({ lifecycle }) {
                LogUtils.e(it)
            }
        }
    }
}