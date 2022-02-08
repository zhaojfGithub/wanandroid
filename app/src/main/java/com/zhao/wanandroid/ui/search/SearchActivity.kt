package com.zhao.wanandroid.ui.search

import android.content.Intent
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexboxLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.databinding.ActivitySearchBinding
import com.zhao.wanandroid.local.DataState
import com.zhao.wanandroid.ui.main.activity.MainActivity
import com.zhao.wanandroid.ui.search.detail.SearchDetailActivity
import com.zhao.wanandroid.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseVmActivity<SearchViewModel, ActivitySearchBinding>() {

    companion object {
        fun start(context: AppCompatActivity) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val hotSearchAdapter by lazy { HotSearchAdapter() }
    private val historySearchAdapter by lazy { HistorySearchAdapter() }

    override fun getLayoutId() = R.layout.activity_search

    override fun initView() {
        binding.include.toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(binding.include.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.recyclerViewHot.run {
            layoutManager = FlexboxLayoutManager(this@SearchActivity)
            adapter = hotSearchAdapter
            hotSearchAdapter.onItemClick { _, str ->
                startDisposeText(str)
            }
        }

        binding.recyclerViewHistory.run {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = historySearchAdapter
            historySearchAdapter.onItemClick { i, str ->
                if (i == 1) {
                    startDisposeText(str)
                } else {
                    viewModel.getHistorySearchOperate(str, DataState.DELETE)
                }
            }
        }
    }

    override fun initOnclick() {
        binding.tvSearchClear.setOnClickListener {
            viewModel.getHistorySearchOperate(state = DataState.DELETE_ALL)
            viewModel.getHistorySearchOperate(state = DataState.SELECT)
        }
    }

    override fun initData() {
        viewModel.getHotSearch()
        viewModel.getHistorySearchOperate(null, DataState.SELECT)
    }

    override fun observe() {
        super.observe()
        viewModel.apply {
            hotSearch.observe({ lifecycle }) {
                hotSearchAdapter.refreshAllItem(it)
            }
            historySearch.observe({ lifecycle }) {
                historySearchAdapter.refreshAllItem(it)
            }
        }
    }

    override fun getModelClass(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.onActionViewExpanded()
        searchView.queryHint = getString(R.string.discover_knowledge)
        searchView.setOnQueryTextListener(queryTextListener)
        searchView.isSubmitButtonEnabled = true
        try {
            val field = searchView.javaClass.getDeclaredField("mGoButton")
            field.isAccessible = true
            val mGoButton = field.get(searchView) as ImageView
            mGoButton.setImageResource(R.drawable.ic_search)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return super.onCreateOptionsMenu(menu)
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            LogUtils.e("SearchActivity",query.toString())
            if (query != null) {
                startDisposeText(query)
            }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }

    /**
     * 1.更新数据库
     * 2.更新列表
     * 3.跳转
     */
    private fun startDisposeText(text: String) {
        if (text.isEmpty()) return
        viewModel.getHistorySearchOperate(text, DataState.UPDATE)
        historySearchAdapter.updateItemIndex(text)
        SearchDetailActivity.start(this, text)
    }
}