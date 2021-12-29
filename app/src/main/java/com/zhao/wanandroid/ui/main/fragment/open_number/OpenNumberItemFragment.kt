package com.zhao.wanandroid.ui.main.fragment.open_number

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.AdapterInterface
import com.zhao.wanandroid.base.adapter.RecyclerMoveInterface
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.FragmentOpenNumberItemBinding
import com.zhao.wanandroid.utils.LogUtils
import com.zhao.wanandroid.weight.extend.isSlideBottom
import com.zhao.wanandroid.weight.extend.smoothScrollToHeaderPosition

/**
 *创建时间： 2021/12/21
 *编   写：  zjf
 *页面功能:
 */
class OpenNumberItemFragment : BaseVmFragment<OpenNumberViewModel, FragmentOpenNumberItemBinding>(), LoadMoreInterface<ArticleItemBean>, RecyclerMoveInterface {

    companion object {
        private const val FRAGMENT_ID: String = "id"
        private const val INDEX: String = "index"

        @JvmStatic
        fun newInstance(id: Int, index: Int): Fragment {
            LogUtils.e("OpenNumberItemFragment进行了初始化")
            val fragment = OpenNumberItemFragment()
            val bundle = Bundle()
            bundle.putInt(FRAGMENT_ID, id)
            bundle.putInt(INDEX, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var fragmentId: Int? = null
    private var index: Int? = null


    private val adapter: AdapterInterface<ArticleItemBean> by lazy { OpenNumberItemAdapter() }

    override fun initData() {
        arguments?.also {
            fragmentId = it.getInt(FRAGMENT_ID)
            index = it.getInt(INDEX)
        }
    }

    override fun lazyData() {
        super.lazyData()
        if (fragmentId == null || index == null) return
        viewModel.getWxArticle(fragmentId!!, index!!, AppState.LoadingState.REFRESH)
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter as OpenNumberItemAdapter
        binding.recyclerView.isSlideBottom(2) {
            viewModel.getWxArticle(fragmentId!!, index!!, AppState.LoadingState.LOAD_MORE)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getWxArticle(fragmentId!!, index!!, AppState.LoadingState.REFRESH)
        }
    }

    override fun observer() {
        //因为要取消注册，所以进行重写，Live 不可以在一个Activity内进行重复注册，具体可看LiveData.observer的Lambda
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_open_number_item
    }

    override fun getModelClass(): Class<OpenNumberViewModel> {
        return OpenNumberViewModel::class.java
    }

    override fun addData(loadingState: AppState.LoadingState, data: List<ArticleItemBean>) {
        when (loadingState) {
            AppState.LoadingState.REFRESH -> {
                adapter.dismissFooterView()
                adapter.refreshAllItem(data)
                binding.swipeRefreshLayout.isRefreshing = false
            }
            AppState.LoadingState.LOAD_MORE -> {
                adapter.addFooterItemAllData(data)
            }
        }
    }

    override fun moveHeader() {
        binding.recyclerView.smoothScrollToHeaderPosition()
    }

    override fun showFooter() {
        LogUtils.e("调用显示了尾部")
        adapter.showFooterView()
    }


}