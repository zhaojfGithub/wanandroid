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
import com.zhao.wanandroid.extend.isSlideBottom
import com.zhao.wanandroid.weight.extend.smoothScrollToHeaderPosition

/**
 *创建时间： 2021/12/21
 *编   写：  zjf
 *页面功能:
 */
class OpenNumberItemFragment : BaseVmFragment<OpenNumberViewModel, FragmentOpenNumberItemBinding>(), LoadMoreInterface<ArticleItemBean>, RecyclerMoveInterface {

    companion object {
        private const val ID: String = "id"

        @JvmStatic
        fun newInstance(id: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt(ID, id)
            val fragment = OpenNumberItemFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var id: Int? = null

    private val adapter: AdapterInterface<ArticleItemBean> by lazy { OpenNumberItemAdapter() }

    override fun initData() {
        val bundle = this.arguments
        id = bundle!!.getInt(ID)
        viewModel.getWxArticle(id!!, AppState.LoadingState.REFRESH)
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter as OpenNumberItemAdapter
        binding.recyclerView.isSlideBottom {
            viewModel.getWxArticle(id!!, AppState.LoadingState.LOAD_MORE)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getWxArticle(id!!, AppState.LoadingState.REFRESH)
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


}