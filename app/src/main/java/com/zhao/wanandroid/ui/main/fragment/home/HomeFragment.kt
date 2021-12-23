package com.zhao.wanandroid.ui.main.fragment.home


import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.RecyclerMoveInterface
import com.zhao.wanandroid.databinding.FragmentHomeBinding
import com.zhao.wanandroid.extend.isSlideBottom
import com.zhao.wanandroid.weight.extend.smoothScrollToHeaderPosition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseVmFragment<HomeViewModel, FragmentHomeBinding>(), RecyclerMoveInterface {


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    private val adapter: HomeAdapter by lazy { HomeAdapter() }

    override fun initData() {
        viewModel.initHomeData()
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.isSlideBottom {
            viewModel.loadArticleData()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.initHomeData()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun observer() {
        viewModel.apply {
            banner.observe({ lifecycle }) {
                adapter.changedBanner(it)
            }
            article.observe({ lifecycle }) {
                if (it.curPage == 1) {
                    adapter.refreshAllItem(it.data)
                } else {
                    adapter.addFooterItemAllData(it.data)
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