package com.zhao.wanandroid.ui.main.fragment.open_number

import com.google.android.material.tabs.TabLayoutMediator
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.RecyclerMoveInterface
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.databinding.FragmentOpenNumberBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OpenNumberFragment : BaseVmFragment<OpenNumberViewModel, FragmentOpenNumberBinding>(), RecyclerMoveInterface {

    companion object {
        @JvmStatic
        fun newInstance() = OpenNumberFragment()
    }

    private val viewPageAdapter: OpenNumberViewPageAdapter by lazy { OpenNumberViewPageAdapter(lifecycle, requireActivity().supportFragmentManager) }

    override fun initData() {
        viewModel.getWxParentArticle()
    }

    override fun initView() {
        binding.viewPage.adapter = viewPageAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPage) { tab, position ->
            tab.text = viewPageAdapter.getTitle(position)
        }.attach()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_open_number
    }

    override fun getModelClass(): Class<OpenNumberViewModel> {
        return OpenNumberViewModel::class.java
    }

    override fun observer() {
        super.observer()
        binding.data = viewModel
        viewModel.apply {
            wxBoxArticle.observe({ lifecycle }) {
                viewPageAdapter.addAllData(it)
            }
            wxArticle.observe({ lifecycle }) {
                val fragment: LoadMoreInterface<ArticleItemBean> = viewPageAdapter.getFragment(binding.viewPage.currentItem) as OpenNumberItemFragment
                fragment.addData(it.first, it.second)
            }
            isLoadingEnd.observe({lifecycle}){
                val fragment: LoadMoreInterface<ArticleItemBean> = viewPageAdapter.getFragment(binding.viewPage.currentItem) as OpenNumberItemFragment
                fragment.showFooter()
            }
        }
    }

    override fun moveHeader() {
        val fragment: RecyclerMoveInterface = viewPageAdapter.getFragment(binding.viewPage.currentItem) as RecyclerMoveInterface
        fragment.moveHeader()
    }

}