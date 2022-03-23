package com.zhao.wanandroid.ui.common

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.fragment.LoadMoreInterface
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.BaseLayoutBinding
import com.zhao.wanandroid.ui.web.WebActivity
import com.zhao.wanandroid.utils.LogUtils
import com.zhao.wanandroid.weight.extend.isSlideBottom

/**
 *创建时间： 2022/2/23
 *编   写：  zjf
 *页面功能:
 */
class CollectFragment : BaseVmFragment<CommonViewModel, BaseLayoutBinding>(),LoadMoreInterface<ArticleItemBean> {

    companion object {
        @JvmStatic
        fun newInstance() = CollectFragment()
    }

    private val adapter: CollectAdapter by lazy { CollectAdapter() }

    override fun getModelClass(): Class<CommonViewModel> {
        return CommonViewModel::class.java
    }

    override fun initData() {
        viewModel.getCollectList(AppState.LoadingState.REFRESH)
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = ConcatAdapter(config,adapter,footerAdapter)
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getCollectList(AppState.LoadingState.REFRESH)
        }
        binding.recyclerView.isSlideBottom {
            viewModel.getCollectList()
        }
    }

    override fun initClick() {
        super.initClick()
        binding.floatingActionButton.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }
        adapter.onItemClick { i, articleItemBean ->
            if (i == 0){
                WebActivity.start(requireContext(),articleItemBean.title,articleItemBean.link)
            }else{
                viewModel.cancelCollect(articleItemBean)
            }
        }
    }
    override fun getLayoutId(): Int {
        return R.layout.base_layout
    }


    override fun observer() {
        viewModel.collectCancelBean.observe({lifecycle}){
            adapter.removeItemData(it)
        }
        viewModel.allCollect.observe({lifecycle}){
            LogUtils.e("有数据回调")
            if (binding.swipeRefreshLayout.isRefreshing){
                adapter.refreshAllItem(it.data)
                binding.swipeRefreshLayout.isRefreshing = false
            }else{
                adapter.addFooterItemAllData(it.data)
            }
        }
    }

    override fun addData(data: List<ArticleItemBean>) {

    }

    override fun viewState(state: AppState.LoadingState) {
        footerAdapter.updateViewState(state)
    }
}