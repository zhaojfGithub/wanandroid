package com.zhao.wanandroid.ui.main.fragment.home


import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.business.RecyclerMoveInterface
import com.zhao.wanandroid.base.adapter.footer.BaseSimplenessFooterAdapter
import com.zhao.wanandroid.databinding.FragmentHomeBinding
import com.zhao.wanandroid.ui.web.WebActivity
import com.zhao.wanandroid.utils.LogUtils
import com.zhao.wanandroid.weight.extend.isSlideBottom
import com.zhao.wanandroid.weight.extend.smoothScrollToHeaderPosition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseVmFragment<HomeViewModel, FragmentHomeBinding>(), RecyclerMoveInterface {


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    private val homeAdapter: HomeAdapter by lazy { HomeAdapter() }
    private val bannerAdapter: BannerHeaderAdapter by lazy { BannerHeaderAdapter() }

    override fun initData() {

    }

    override fun lazyData() {
        super.lazyData()
        viewModel.initHomeData()
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ConcatAdapter(config, bannerAdapter, homeAdapter, footerAdapter)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.isSlideBottom(2) {
            viewModel.loadArticleData()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            if (binding.swipeRefreshLayout.isRefreshing) {
                viewModel.initHomeData()
            }
        }
        homeAdapter.onItemClick { _, articleItemBean ->
            WebActivity.start(requireActivity(), articleItemBean.title, articleItemBean.link)
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
                bannerAdapter.changedBanner(it)
            }
            article.observe({ lifecycle }) {
                if (binding.swipeRefreshLayout.isRefreshing) {
                    homeAdapter.refreshAllItem(it.data)
                    binding.swipeRefreshLayout.isRefreshing = false
                } else {
                    homeAdapter.addFooterItemAllData(it.data)
                }
            }
            viewSate.observe({ lifecycle }) {
                footerAdapter.updateViewState(it)
            }
        }
    }

    override fun moveHeader() {
        binding.recyclerView.smoothScrollToHeaderPosition()
    }
}