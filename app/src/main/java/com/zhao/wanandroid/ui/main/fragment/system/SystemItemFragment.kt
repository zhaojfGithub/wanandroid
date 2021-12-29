package com.zhao.wanandroid.ui.main.fragment.system

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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

    private val adapter : SystemItemAdapter by lazy { SystemItemAdapter() }

    override fun initData() {
        viewModel.getSystemTree()
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        binding.recyclerView.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getSystemTree()
        }
    }

    override fun observer() {
        viewModel.apply {
            systemTree.observe({lifecycle}){
                adapter.refreshAllItem(it)
                if (binding.swipeRefreshLayout.isRefreshing){
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system_item
    }

    override fun getModelClass(): Class<SystemViewModel> {
        return SystemViewModel::class.java
    }
}