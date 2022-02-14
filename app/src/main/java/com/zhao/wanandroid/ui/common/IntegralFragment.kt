package com.zhao.wanandroid.ui.common

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.base.adapter.BindingViewHolder
import com.zhao.wanandroid.base.adapter.body.BaseSimplenessAdapter
import com.zhao.wanandroid.bean.IntegralItemBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.BaseRecyclerBinding
import com.zhao.wanandroid.databinding.FragmentIntegralBinding
import com.zhao.wanandroid.databinding.ItemIntegralBinding
import com.zhao.wanandroid.ui.web.WebActivity
import com.zhao.wanandroid.weight.extend.isSlideBottom

/**
 *创建时间： 2022/2/11
 *编   写：  zjf
 *页面功能:
 */
class IntegralFragment : BaseVmFragment<CommonViewModel,FragmentIntegralBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = IntegralFragment()
    }

    private val integralAdapter by lazy { IntegralAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getModelClass(): Class<CommonViewModel> {
        return CommonViewModel::class.java
    }

    override fun initView() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.title = getString(R.string.drawer_integral)
        (activity as CommonActivity).setSupportActionBar(binding.toolbar)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        binding.recyclerView.adapter = ConcatAdapter(config,integralAdapter,footerAdapter)
        binding.recyclerView.isSlideBottom {
            if (!binding.swipeRefreshLayout.isRefreshing){
                viewModel.getIntegralDetail()
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            if (!binding.swipeRefreshLayout.isRefreshing){
                viewModel.getIntegralDetail(AppState.LoadingState.REFRESH)
                viewModel.getUserIntegral()
            }
        }
    }

    override fun observer() {
        viewModel.integralList.observe({lifecycle}){
            if (binding.swipeRefreshLayout.isRefreshing){
                integralAdapter.refreshAllItem(it.data)
                binding.swipeRefreshLayout.isRefreshing = false
            }else{
                integralAdapter.addFooterItemAllData(it.data)
            }
        }
        viewModel.viewSate.observe({lifecycle}){
            footerAdapter.updateViewState(it)
        }
        viewModel.userIntegral.observe({lifecycle}){
            binding.tvUserCoin.text = it.coinCount.toString()
        }
    }

    override fun initData() {
        viewModel.getIntegralDetail(AppState.LoadingState.REFRESH)
        viewModel.getUserIntegral()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_integral
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_integral,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_query ->{
                WebActivity.start(requireContext(),"本站积分规则 - 玩Android -玩Android.com","www.baidu.com")
            }
        }
        return true
    }

    private class IntegralAdapter : BaseSimplenessAdapter<ItemIntegralBinding, IntegralItemBean>() {
        override fun layoutId(): Int {
            return R.layout.item_integral
        }

        override fun onBindBindingViewHolder(holder: BindingViewHolder<ItemIntegralBinding>, position: Int) {
            holder.binding.data = list[position]
        }
    }
}