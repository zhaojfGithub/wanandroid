package com.zhao.wanandroid.ui.main.fragment.home


import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.databinding.FragmentHomeBinding
import com.zhao.wanandroid.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseVmFragment<HomeViewModel, FragmentHomeBinding>() {


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    private val adapter: HomeAdapter by lazy { HomeAdapter() }

    override fun initData() {
        //viewModel.initHomeData()
    }

    override fun initView() {
        //binding.recyclerView.layoutManager = LinearLayoutManager(context)
        //binding.recyclerView.adapter = adapter
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun observer() {
        binding.data = viewModel
        viewModel.apply {
            banner.observe({ lifecycle }) {
                adapter.changedBanner(it)
            }
            article.observe({ lifecycle }) {
                if (it.curPage == 1) {
                    adapter.refreshArticle(it.data)
                } else {
                    adapter.addAllArticle(it.data)
                }
            }
        }
    }
}