package com.zhao.wanandroid.ui.search.detail

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.databinding.ActivitySearchDetailBinding
import com.zhao.wanandroid.ui.search.SearchActivity
import com.zhao.wanandroid.ui.search.SearchViewModel
import com.zhao.wanandroid.ui.web.WebActivity
import com.zhao.wanandroid.weight.extend.isSlideBottom
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailActivity : BaseVmActivity<SearchViewModel,ActivitySearchDetailBinding>() {

    companion object {
        private const val TEXT_DETAIL = "detail"
        fun start(context: AppCompatActivity,text:String) {
            val intent = Intent(context, SearchDetailActivity::class.java)
            intent.putExtra(TEXT_DETAIL,text)
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
        binding.recyclerView.adapter = adapter
        binding.recyclerView.isSlideBottom {
            val text = binding.include.toolbar.title
            if (text != null && text.isNotEmpty()){
                viewModel.getSearchQuery(text = text.toString())
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            if (binding.swipeRefreshLayout.isRefreshing){
                val text = binding.include.toolbar.title
                if (text != null && text.isNotEmpty()){
                    viewModel.getSearchQuery(text = text.toString())
                }
            }
        }
    }

    override fun initOnclick() {
        adapter.onItemClick { type, articleItemBean ->
            if (type == 1){
                WebActivity.start(this,articleItemBean.title,articleItemBean.link)
            }else{
                //收藏
                if (loginAssist()){

                }
            }
        }
    }

    override fun initData() {
        val text = intent.getStringExtra(TEXT_DETAIL)
        text?.also {
            binding.include.toolbar.title = it
            viewModel.getSearchQuery(text = it)
        }
    }

    override fun getModelClass(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
            }
        }
        return true
    }

}