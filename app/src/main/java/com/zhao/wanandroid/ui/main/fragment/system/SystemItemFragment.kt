package com.zhao.wanandroid.ui.main.fragment.system

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.business.RecyclerMoveInterface
import com.zhao.wanandroid.databinding.FragmentSystemItemBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *创建时间： 2021/12/28
 *编   写：  zjf
 *页面功能:
 */
@AndroidEntryPoint
class SystemItemFragment : BaseVmFragment<SystemViewModel, FragmentSystemItemBinding>(), RecyclerMoveInterface {

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return SystemItemFragment()
        }
    }

    private val adapter : SystemItemAdapter by lazy { SystemItemAdapter() }

    override fun initData() {
        //viewModel.getSystemTree()
    }

    override fun lazyData() {
        super.lazyData()
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

    override fun moveHeader() {
        binding.recyclerView.smoothScrollToPosition(0)
    }
}