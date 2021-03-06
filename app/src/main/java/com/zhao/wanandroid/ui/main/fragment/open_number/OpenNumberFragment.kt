package com.zhao.wanandroid.ui.main.fragment.open_number

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.business.RecyclerMoveInterface
import com.zhao.wanandroid.base.fragment.LoadMoreInterface
import com.zhao.wanandroid.base.view_page.BaseViewPageAdapter
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.databinding.FragmentOpenNumberBinding
import com.zhao.wanandroid.utils.ThemeColorUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OpenNumberFragment : BaseVmFragment<OpenNumberViewModel, FragmentOpenNumberBinding>(), RecyclerMoveInterface {

    companion object {
        @JvmStatic
        fun newInstance() = OpenNumberFragment()
    }

    private val viewPageAdapter: BaseViewPageAdapter by lazy { BaseViewPageAdapter(lifecycle, requireActivity().supportFragmentManager) }


    override fun initData() {

    }

    override fun lazyData() {
        super.lazyData()
        viewModel.getWxParentArticle()
    }


    override fun initView() {
        binding.viewPage.adapter = viewPageAdapter
        binding.tabLayout.setBackgroundColor(ThemeColorUtil.getThemeColor(requireContext(), R.attr.backgroundColor))
        binding.tabLayout.setTabTextColors(ThemeColorUtil.getThemeColor(requireContext(), R.attr.colorOnPrimary),
            ThemeColorUtil.getThemeColor(requireContext(), R.attr.colorPrimary))
        TabLayoutMediator(binding.tabLayout, binding.viewPage) { tab, position ->
            tab.text = viewPageAdapter.getFragmentTitle(position)
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
                val fragmentList: MutableList<Pair<String, Fragment>> = ArrayList()
                it.forEachIndexed { index, openNumberBoxBean ->
                    fragmentList.add(Pair(openNumberBoxBean.name, OpenNumberItemFragment.newInstance(openNumberBoxBean.id, index)))
                }
                viewPageAdapter.refreshFragments(fragmentList)
            }
            wxArticle.observe({ lifecycle }) {
                val fragment: LoadMoreInterface<ArticleItemBean> = viewPageAdapter.getFragment(binding.viewPage.currentItem) as OpenNumberItemFragment
                fragment.addData(it)
            }
            viewSate.observe({lifecycle}){
                val fragment: LoadMoreInterface<ArticleItemBean> = viewPageAdapter.getFragment(binding.viewPage.currentItem) as OpenNumberItemFragment
                fragment.viewState(it)
            }
        }
    }

    override fun moveHeader() {
        val fragment: RecyclerMoveInterface = viewPageAdapter.getFragment(binding.viewPage.currentItem) as RecyclerMoveInterface
        fragment.moveHeader()
    }

}