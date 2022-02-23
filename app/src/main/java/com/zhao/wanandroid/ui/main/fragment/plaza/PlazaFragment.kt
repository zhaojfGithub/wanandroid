package com.zhao.wanandroid.ui.main.fragment.plaza

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.business.RecyclerMoveInterface
import com.zhao.wanandroid.databinding.FragmentPlazaBinding
import com.zhao.wanandroid.ui.web.WebActivity
import com.zhao.wanandroid.weight.extend.isSlideBottom
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

    }

    override fun lazyData() {
        super.lazyData()
        viewModel.refreshPlazaData()
    }


    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = ConcatAdapter(config,adapter,footerAdapter)
        binding.recyclerView.isSlideBottom {
            viewModel.loadPlazaData()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshPlazaData()
        }
    }

    override fun initClick() {
        super.initClick()
        adapter.onItemClick { _, articleItemBean ->
            WebActivity.start(requireContext(),articleItemBean.title,articleItemBean.link)
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
                if (binding.swipeRefreshLayout.isRefreshing) {
                    adapter.refreshAllItem(it.data)
                    binding.swipeRefreshLayout.isRefreshing = false
                } else {
                    adapter.addFooterItemAllData(it.data)
                }
            }
            viewSate.observe({lifecycle}){
                footerAdapter.updateViewState(it)
            }
        }
    }

    override fun moveHeader() {
        binding.recyclerView.smoothScrollToHeaderPosition()
    }
}