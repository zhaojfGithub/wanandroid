package com.zhao.wanandroid.ui.main.fragment.system

import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.RecyclerMoveInterface
import com.zhao.wanandroid.base.view_page.BaseViewPageAdapter
import com.zhao.wanandroid.databinding.FragmentSystemBinding
import com.zhao.wanandroid.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SystemFragment : BaseVmFragment<SystemViewModel, FragmentSystemBinding>(),RecyclerMoveInterface {

    companion object {
        @JvmStatic
        fun newInstance() = SystemFragment()
    }

    private val viewPageAdapter by lazy { BaseViewPageAdapter(lifecycle, requireActivity().supportFragmentManager) }

    override fun initData() {
    }

    override fun initView() {
        val itemFragment = Pair("体系",SystemItemFragment.newInstance())
        val navigationFragment = Pair( "导航",SystemNavigationFragment.newInstance())
        val fragmentList : List<Pair<String, Fragment>> = arrayListOf(itemFragment,navigationFragment)
        binding.viewPage.adapter = viewPageAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPage) { tab, position ->
            tab.text = viewPageAdapter.getFragmentTitle(position)
        }.attach()
        viewPageAdapter.refreshFragments(fragmentList)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun getModelClass(): Class<SystemViewModel> {
        return SystemViewModel::class.java
    }

    override fun moveHeader() {

    }

}