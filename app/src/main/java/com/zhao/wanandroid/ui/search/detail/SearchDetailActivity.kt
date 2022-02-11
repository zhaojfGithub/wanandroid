package com.zhao.wanandroid.ui.search.detail

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.ActivitySearchDetailBinding
import com.zhao.wanandroid.ui.search.SearchViewModel
import com.zhao.wanandroid.ui.web.WebActivity
import com.zhao.wanandroid.utils.LogUtils
import com.zhao.wanandroid.weight.extend.isSlideBottom
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailActivity : BaseVmActivity<SearchViewModel, ActivitySearchDetailBinding>() {

    companion object {
        private const val TEXT_DETAIL = "detail"
        fun start(context: AppCompatActivity, text: String) {
            val intent = Intent(context, SearchDetailActivity::class.java)
            intent.putExtra(TEXT_DETAIL, text)
            context.startActivity(intent)
        }
    }

    private val adapter by lazy { SearchDetailAdapter() }

    override fun getLayoutId(): Int {
        return R.layout.activity_search_detail
    }

    override fun initView() {
        binding.include.toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(binding.include.toolbar)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ConcatAdapter(config,adapter,footerAdapter)
        binding.recyclerView.isSlideBottom(1) {
            val text = supportActionBar?.title
            if (text != null && text.isNotEmpty()) {
                viewModel.getSearchQuery(text = text.toString())
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            if (binding.swipeRefreshLayout.isRefreshing) {
                val text = supportActionBar?.title
                if (text != null && text.isNotEmpty()) {
                    viewModel.getSearchQuery(text = text.toString())
                }
            }
        }
    }

    override fun initOnclick() {
        adapter.onItemClick { type, articleItemBean ->
            if (type == 1) {
                WebActivity.start(this, articleItemBean.title, articleItemBean.link)
            } else {
                //收藏
                if (loginAssist()) {
                    viewModel.updateCollect(bean = articleItemBean)
                }
            }
        }
        binding.floatingActionButton.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }
    }

    override fun initData() {
        val text = intent.getStringExtra(TEXT_DETAIL)
        LogUtils.e(TAG, text.toString())
        if (text != null && supportActionBar != null){
            supportActionBar!!.title = text
            viewModel.getSearchQuery(text = text,AppState.LoadingState.REFRESH)
        }
    }

    override fun observe() {
        super.observe()
        viewModel.apply {
            searchQuery.observe({lifecycle}){
                if (binding.swipeRefreshLayout.isRefreshing){
                    adapter.refreshAllItem(it.data)
                    binding.swipeRefreshLayout.isRefreshing = false
                }else{
                    adapter.addFooterItemAllData(it.data)
                }
            }
            viewSate.observe({lifecycle}){
                footerAdapter.updateViewState(it)
            }
            refreshBean.observe({lifecycle}){
                if (it.collect){
                    Toast.makeText(MyApplication.getInstance().applicationContext,getString(R.string.collect_succeed),Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(MyApplication.getInstance().applicationContext,getString(R.string.collect_cancel),Toast.LENGTH_SHORT).show()
                }
                adapter.refreshItem(it)
            }
        }
    }

    override fun getModelClass(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        LogUtils.e(TAG,"有回调1")
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

}