package com.zhao.wanandroid.ui.main.fragment.system

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.databinding.FragmentSystemNavigationBinding
import com.zhao.wanandroid.weight.extend.smoothScrollToMiddlePosition
import com.zhao.wanandroid.weight.extend.smoothScrollToTopPosition
import dagger.hilt.android.AndroidEntryPoint

/**
 *创建时间： 2021/12/28
 *编   写：  zjf
 *页面功能:
 */
@AndroidEntryPoint
class SystemNavigationFragment : BaseVmFragment<SystemViewModel, FragmentSystemNavigationBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return SystemNavigationFragment()
        }
    }

    private val leftAdapter by lazy { SystemNavigationLeftAdapter() }
    private val rightAdapter by lazy { SystemNavigationRightAdapter() }

    override fun initData() {
        viewModel.getNavigation()
    }

    override fun initView() {

        binding.recyclerViewLeft.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewLeft.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        binding.recyclerViewLeft.adapter = leftAdapter

        binding.recyclerViewRight.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewRight.adapter = rightAdapter
        
        leftAdapter.onItemClick { index, bean ->
            bean.isSelect = true
            leftAdapter.refreshItem(bean)
            binding.recyclerViewRight.smoothScrollToTopPosition(index)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system_navigation
    }

    override fun getModelClass(): Class<SystemViewModel> {
        return SystemViewModel::class.java
    }

    override fun observer() {
        viewModel.apply {
            navigation.observe({ lifecycle }) {
                rightAdapter.refreshAllItem(it)
                leftAdapter.refreshAllItem(it)
            }
        }
    }
}