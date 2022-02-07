package com.zhao.wanandroid.ui.main.fragment.home


import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.RecyclerMoveInterface
import com.zhao.wanandroid.databinding.FragmentHomeBinding
import com.zhao.wanandroid.ui.web.WebActivity
import com.zhao.wanandroid.weight.extend.isSlideBottom
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

    }

    override fun lazyData() {
        super.lazyData()
        viewModel.initHomeData()
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.isSlideBottom() {
            viewModel.loadArticleData()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            if (binding.swipeRefreshLayout.isRefreshing){
                viewModel.initHomeData()
            }
        }
        adapter.onItemClick { _, articleItemBean ->
            WebActivity.start(requireActivity(),articleItemBean.link)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun observer() {
        super.observer()
        viewModel.apply {
            banner.observe({ lifecycle }) {
                adapter.changedBanner(it)
            }
            article.observe({ lifecycle }) {
                if (binding.swipeRefreshLayout.isRefreshing) {
                    adapter.refreshAllItem(it.data)
                    binding.swipeRefreshLayout.isRefreshing = false
                } else {
                    adapter.addFooterItemAllData(it.data)
                }
            }
        }
    }

    override fun moveHeader() {
        binding.recyclerView.smoothScrollToHeaderPosition()
    }
}