package com.zhao.wanandroid.ui.main.fragment.open_number

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.body.AdapterInterface
import com.zhao.wanandroid.base.adapter.business.RecyclerMoveInterface
import com.zhao.wanandroid.base.adapter.footer.BaseSimplenessFooterAdapter
import com.zhao.wanandroid.base.fragment.LoadMoreInterface
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.FragmentOpenNumberItemBinding
import com.zhao.wanandroid.weight.extend.isSlideBottom
import com.zhao.wanandroid.weight.extend.smoothScrollToHeaderPosition

/**
 *创建时间： 2021/12/21
 *编   写：  zjf
 *页面功能:
 */
class OpenNumberItemFragment : BaseVmFragment<OpenNumberViewModel, FragmentOpenNumberItemBinding>(), LoadMoreInterface<ArticleItemBean>, RecyclerMoveInterface {

    companion object {
        private const val FRAGMENT_ID: String = "fragment_id"
        private const val INDEX: String = "index"

        @JvmStatic
        fun newInstance(id: Int, index: Int): Fragment {
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


    private val itemAdapter: OpenNumberItemAdapter by lazy { OpenNumberItemAdapter() }

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
        binding.recyclerView.adapter = ConcatAdapter(config,itemAdapter,footerAdapter)
        binding.recyclerView.isSlideBottom {
            if (fragmentId == null || index == null) return@isSlideBottom
            viewModel.getWxArticle(fragmentId!!, index!!, AppState.LoadingState.LOAD_MORE)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            if (fragmentId == null || index == null) return@setOnRefreshListener
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

    override fun addData(data: List<ArticleItemBean>) {
        if (binding.swipeRefreshLayout.isRefreshing) {
            itemAdapter.refreshAllItem(data)
            binding.swipeRefreshLayout.isRefreshing = false
        } else {
            itemAdapter.addFooterItemAllData(data)
        }
    }

    override fun viewState(state: AppState.LoadingState) {
        footerAdapter.updateViewState(state)
    }

    override fun moveHeader() {
        binding.recyclerView.smoothScrollToHeaderPosition()
    }


}