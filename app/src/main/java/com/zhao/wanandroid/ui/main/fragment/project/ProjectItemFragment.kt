package com.zhao.wanandroid.ui.main.fragment.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.business.RecyclerMoveInterface
import com.zhao.wanandroid.base.fragment.LoadMoreInterface
import com.zhao.wanandroid.bean.ProjectItemBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.IncludeRecyclerBinding
import com.zhao.wanandroid.weight.extend.isSlideBottom
import com.zhao.wanandroid.weight.extend.smoothScrollToHeaderPosition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectItemFragment : BaseVmFragment<ProjectViewModel, IncludeRecyclerBinding>(), LoadMoreInterface<ProjectItemBean>, RecyclerMoveInterface {

    companion object {
        const val FRAGMENT_ID = "project_id"
        const val FRAGMENT_INDEX = "index"

        @JvmStatic
        fun newInstance(id: Int, index: Int): Fragment {
            val fragment = ProjectItemFragment()
            val bundle = Bundle()
            bundle.putInt(FRAGMENT_ID, id)
            bundle.putInt(FRAGMENT_INDEX, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var projectId: Int? = null
    private var index: Int? = null

    private val adapter: ProjectItemAdapter by lazy { ProjectItemAdapter() }

    override fun initData() {
        arguments?.also {
            projectId = it.getInt(FRAGMENT_ID)
            index = it.getInt(FRAGMENT_INDEX)
        }
    }

    override fun lazyData() {
        super.lazyData()
        if (projectId == null || index == null) return
        viewModel.getProjectItem(projectId!!, index!!, AppState.LoadingState.REFRESH)
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        binding.recyclerView.adapter = adapter
        binding.recyclerView.isSlideBottom(1) {
            if (projectId == null || index == null) return@isSlideBottom
            viewModel.getProjectItem(projectId!!, index!!, AppState.LoadingState.LOAD_MORE)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            if (projectId == null || index == null) return@setOnRefreshListener
            viewModel.getProjectItem(projectId!!, index!!, AppState.LoadingState.REFRESH)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.include_recycler
    }

    override fun getModelClass(): Class<ProjectViewModel> {
        return ProjectViewModel::class.java
    }

    override fun observer() {

    }

    override fun addData(data: List<ProjectItemBean>) {
        if (binding.swipeRefreshLayout.isRefreshing) {
            adapter.refreshAllItem(data)
            binding.swipeRefreshLayout.isRefreshing = false
        } else {
            adapter.addFooterItemAllData(data)
        }
    }


    override fun moveHeader() {
        binding.recyclerView.smoothScrollToHeaderPosition()
    }

    override fun viewState(state: AppState.LoadingState) {
        footerAdapter.updateViewState(state)
    }


}