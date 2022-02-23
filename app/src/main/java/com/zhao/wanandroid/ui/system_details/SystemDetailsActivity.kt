package com.zhao.wanandroid.ui.system_details

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.base.adapter.business.RecyclerMoveInterface
import com.zhao.wanandroid.base.fragment.LoadMoreInterface
import com.zhao.wanandroid.base.view_page.BaseViewPageAdapter
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.databinding.ActivitySystemDetailsBinding
import com.zhao.wanandroid.databinding.ActivitySystemDetailsBindingImpl
import com.zhao.wanandroid.ui.common.CommonActivity
import com.zhao.wanandroid.ui.main.activity.MainActivity
import com.zhao.wanandroid.ui.main.fragment.open_number.OpenNumberItemFragment
import com.zhao.wanandroid.utils.LogUtils
import com.zhao.wanandroid.utils.ThemeColorUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SystemDetailsActivity : BaseVmActivity<SystemDetailsViewModel, ActivitySystemDetailsBinding>() {

    companion object {
        private const val BEAN_DATA = "bean_data"
        fun start(context: Context, json: String) {
            val intent = Intent(context, SystemDetailsActivity::class.java)
            intent.putExtra(BEAN_DATA, json)
            context.startActivity(intent)
        }
    }

    private val viewPageAdapter: BaseViewPageAdapter by lazy { BaseViewPageAdapter(lifecycle, supportFragmentManager) }

    override fun getLayoutId(): Int {
        return R.layout.activity_system_details
    }

    override fun initView() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.title = getString(R.string.drawer_integral)
        setSupportActionBar(binding.toolbar)
        binding.viewPage.adapter = viewPageAdapter
        binding.tabLayout.setBackgroundColor(ThemeColorUtil.getThemeColor(this, R.attr.backgroundColor))
        binding.tabLayout.setTabTextColors(ThemeColorUtil.getThemeColor(this, R.attr.colorOnPrimary),
            ThemeColorUtil.getThemeColor(this, R.attr.colorPrimary))
        TabLayoutMediator(binding.tabLayout, binding.viewPage) { tab, position ->
            tab.text = viewPageAdapter.getFragmentTitle(position)
        }.attach()
    }

    override fun initOnclick() {
        binding.floatingActionButton.setOnClickListener {
            val fragment: RecyclerMoveInterface = viewPageAdapter.getFragment(binding.viewPage.currentItem) as SystemDetailsFragment
            fragment.moveHeader()
        }
    }

    override fun initData() {
        val json = intent.getStringExtra(BEAN_DATA)
        LogUtils.e(TAG, json.toString())
        if (json != null) {
            viewModel.jsonToBean(json)
        }
    }

    override fun observe() {
        super.observe()
        viewModel.initBean.observe({ lifecycle }) {
            supportActionBar?.title = it.name
            val fragmentList = ArrayList<Pair<String, Fragment>>()
            it.children.forEachIndexed { index, children ->
                fragmentList.add(Pair(children.name, SystemDetailsFragment.newInstance(children.id, index)))
            }
            viewPageAdapter.refreshFragments(fragmentList)
        }
        viewModel.articleItemList.observe({ lifecycle }) {
            val fragment: LoadMoreInterface<ArticleItemBean> = viewPageAdapter.getFragment(binding.viewPage.currentItem) as SystemDetailsFragment
            fragment.addData(it)
        }
        viewModel.viewSate.observe({ lifecycle }) {
            val fragment: LoadMoreInterface<ArticleItemBean> = viewPageAdapter.getFragment(binding.viewPage.currentItem) as SystemDetailsFragment
            fragment.viewState(it)
        }
    }

    override fun getModelClass(): Class<SystemDetailsViewModel> {
        return SystemDetailsViewModel::class.java
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}