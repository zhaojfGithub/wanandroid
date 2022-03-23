package com.zhao.wanandroid.ui.common

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.base.fragment.LoadMoreInterface
import com.zhao.wanandroid.bean.ArticleItemBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.ActivityCommonBinding
import com.zhao.wanandroid.ui.main.activity.MainActivity
import com.zhao.wanandroid.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint

/**
 * 积分，收藏，todo，夜间模式，设置，关于我们，退出登录
 * 分享
 */
@AndroidEntryPoint
class CommonActivity : BaseVmActivity<CommonViewModel, ActivityCommonBinding>() {

    companion object {
        private const val commonState = "activity_state"
        fun start(context: AppCompatActivity, state: AppState.CommonState) {
            val intent = Intent(context, CommonActivity::class.java)
            intent.putExtra(commonState, state.name)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_common
    }

    override fun initView() {
        binding.include.toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(binding.include.toolbar)
    }

    override fun initOnclick() {

    }

    override fun initData() {
        val state = intent.getStringExtra(commonState)
        if (state == null || state.isEmpty()) return
        val fragment: Fragment? = when (state) {
            AppState.CommonState.SHARE.name -> {
                ShareFragment.newInstance()
            }
            AppState.CommonState.INTEGRAL.name ->{
                binding.include.toolbar.visibility = View.GONE
                IntegralFragment.newInstance()
            }
            AppState.CommonState.COLLECT.name ->{
                CollectFragment.newInstance()
            }
            else -> {
                null
            }
        }
        fragment ?: return
        supportFragmentManager.beginTransaction().add(binding.frameLayout.id, fragment).commit()
    }

    override fun getModelClass(): Class<CommonViewModel> {
        return CommonViewModel::class.java
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun observe() {
        super.observe()
        //如果viewModel
        viewModel.viewSate.observe({lifecycle}){
            val fragment = supportFragmentManager.findFragmentById(binding.frameLayout.id)
            if (fragment is LoadMoreInterface<*>){
                fragment.viewState(it)
            }
        }
    }
}