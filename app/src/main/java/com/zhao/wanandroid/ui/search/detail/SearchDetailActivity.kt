package com.zhao.wanandroid.ui.search.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.databinding.ActivitySearchDetailBinding
import com.zhao.wanandroid.ui.search.SearchActivity
import com.zhao.wanandroid.ui.search.SearchViewModel
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

    override fun getLayoutId(): Int {
        return R.layout.activity_search_detail
    }

    override fun initView() {

    }

    override fun initOnclick() {

    }

    override fun initData() {

    }

    override fun getModelClass(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

}