package com.zhao.wanandroid.ui.main.fragment.plaza

import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.RecyclerMoveInterface
import com.zhao.wanandroid.databinding.FragmentPlazaBinding
import com.zhao.wanandroid.extend.isSlideBottom
import com.zhao.wanandroid.weight.extend.smoothScrollToHeaderPosition

import dagger.hilt.android.AndroidEntryPoint

/**
 *创建时间： 2021/12/10
 *编   写：  zjf
 *页面功能:
 */
@AndroidEntryPoint
class PlazaFragment : BaseVmFragment<PlazaViewModel, FragmentPlazaBinding>(), RecyclerMoveInterface {

    companion object {
        @JvmStatic
        fun newInstance() = PlazaFragment()
    }

    private val adapter: PlazaAdapter by lazy { PlazaAdapter() }

    override fun initData() {
        viewModel.refreshPlazaData()
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.isSlideBottom {
            viewModel.loadPlazaData()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshPlazaData()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_plaza
    }

    override fun getModelClass(): Class<PlazaViewModel> = PlazaViewModel::class.java


    override fun observer() {
        binding.data = viewModel
        viewModel.apply {
            article.observe({ lifecycle }) {
                if (it.curPage == 1) {
                    adapter.refreshAllItem(it.data)
                } else {
                    adapter.addHeaderItemAllData(it.data)
                }
            }
            isRefresh.observe({ lifecycle }) {
                binding.swipeRefreshLayout.isRefreshing = it
            }
        }
    }

    override fun moveHeader() {
        binding.recyclerView.smoothScrollToHeaderPosition()
    }
}