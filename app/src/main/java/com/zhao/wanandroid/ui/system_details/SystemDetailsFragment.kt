package com.zhao.wanandroid.ui.system_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.business.RecyclerMoveInterface
import com.zhao.wanandroid.base.fragment.LoadMoreInterface
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.BaseRecyclerBinding
import com.zhao.wanandroid.ui.main.fragment.home.HomeAdapter
import com.zhao.wanandroid.ui.web.WebActivity
import com.zhao.wanandroid.weight.extend.isSlideBottom

/**
 *创建时间： 2022/2/22
 *编   写：  zjf
 *页面功能:
 */
class SystemDetailsFragment : BaseVmFragment<SystemDetailsViewModel, BaseRecyclerBinding>(), LoadMoreInterface<ArticleItemBean>, RecyclerMoveInterface {

    companion object {
        private const val SYSTEM_ID: String = "cid"
        private const val SYSTEM_INDEX: String = "index"

        @JvmStatic
        fun newInstance(id: Int, index: Int): Fragment {
            val fragment = SystemDetailsFragment()
            fragment.arguments = Bundle().apply {
                putInt(SYSTEM_ID, id)
                putInt(SYSTEM_INDEX, index)
            }
            return fragment
        }
    }

    private val adapter: HomeAdapter by lazy { HomeAdapter() }

    private var cid: Int? = null
    private var index: Int? = null

    override fun getModelClass(): Class<SystemDetailsViewModel> {
        return SystemDetailsViewModel::class.java
    }

    override fun initData() {
        arguments?.also {
            cid = it.getInt(SYSTEM_ID)
            index = it.getInt(SYSTEM_INDEX)
        }
    }

    override fun lazyData() {
        super.lazyData()
        if (cid != null && index != null) {
            viewModel.getArticle(index!!, cid!!, AppState.LoadingState.REFRESH)
        }
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = ConcatAdapter(adapter, footerAdapter)
        binding.recyclerView.isSlideBottom {
            if (cid == null || index == null) return@isSlideBottom
            viewModel.getArticle(index!!, cid!!, AppState.LoadingState.LOAD_MORE)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            if (cid == null || index == null) return@setOnRefreshListener
            viewModel.getArticle(index!!, cid!!, AppState.LoadingState.REFRESH)
        }
    }

    override fun initClick() {
        super.initClick()
        adapter.onItemClick { _, articleItemBean ->
            WebActivity.start(requireContext(), articleItemBean.title, articleItemBean.link)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.base_recycler
    }

    override fun moveHeader() {
        binding.recyclerView.smoothScrollToPosition(0)
    }

    override fun addData(data: List<ArticleItemBean>) {
        if (binding.swipeRefreshLayout.isRefreshing) {
            adapter.refreshAllItem(data)
            binding.swipeRefreshLayout.isRefreshing = false
        } else {
            adapter.addFooterItemAllData(data)
        }
    }

    override fun viewState(state: AppState.LoadingState) {
        footerAdapter.updateViewState(state)
    }
}