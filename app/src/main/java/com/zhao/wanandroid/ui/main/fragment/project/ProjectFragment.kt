package com.zhao.wanandroid.ui.main.fragment.project

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.business.RecyclerMoveInterface
import com.zhao.wanandroid.base.fragment.LoadMoreInterface
import com.zhao.wanandroid.base.view_page.BaseViewPageAdapter
import com.zhao.wanandroid.bean.ProjectItemBean
import com.zhao.wanandroid.databinding.FragmentProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectFragment : BaseVmFragment<ProjectViewModel, FragmentProjectBinding>(), RecyclerMoveInterface {

    companion object {
        @JvmStatic
        fun newInstance() = ProjectFragment()
    }

    private val viewPageAdapter by lazy { BaseViewPageAdapter(lifecycle, requireActivity().supportFragmentManager) }

    override fun initData() {

    }

    override fun lazyData() {
        super.lazyData()
        viewModel.getProjectTree()
    }

    override fun initView() {
        binding.viewPage.adapter = viewPageAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPage) { tab, position ->
            tab.text = viewPageAdapter.getFragmentTitle(position)
        }.attach()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun getModelClass(): Class<ProjectViewModel> {
        return ProjectViewModel::class.java
    }

    override fun observer() {
        super.observer()
        viewModel.apply {
            projectTree.observe({ lifecycle }) {
                val pairList = ArrayList<Pair<String, Fragment>>()
                it.forEachIndexed { index, bean ->
                    pairList.add(Pair(bean.name, ProjectItemFragment.newInstance(bean.id, index)))
                }
                viewPageAdapter.refreshFragments(pairList)
            }
            projectItem.observe({ lifecycle }) {
                val fragment: LoadMoreInterface<ProjectItemBean> = viewPageAdapter.getFragment(binding.viewPage.currentItem) as ProjectItemFragment
                //fragment.addData(it.first, it.second)
            }
        }
    }

    override fun moveHeader() {
        val fragment: RecyclerMoveInterface = viewPageAdapter.getFragment(binding.viewPage.currentItem) as RecyclerMoveInterface
        fragment.moveHeader()
    }

}