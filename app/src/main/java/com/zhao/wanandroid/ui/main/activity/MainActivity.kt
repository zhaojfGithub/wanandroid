package com.zhao.wanandroid.ui.main.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.databinding.ActivityMainBinding
import com.zhao.wanandroid.ui.main.fragment.HomeFragment
import com.zhao.wanandroid.ui.main.fragment.OpenNumberFragment
import com.zhao.wanandroid.ui.main.fragment.ProjectFragment
import com.zhao.wanandroid.ui.main.fragment.SystemFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseVmActivity<MainViewModel, ActivityMainBinding>() {


    companion object {
        fun start(context: AppCompatActivity) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
        }
    }

    override fun initView() {
        val fragments = listOf(HomeFragment.newInstance(), SystemFragment.newInstance(), OpenNumberFragment.newInstance(), ProjectFragment.newInstance())
        val adapter = MainViewPageAdapter(lifecycle, supportFragmentManager, fragments)
        binding.viewPage.adapter = adapter
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    binding.viewPage.setCurrentItem(0,true)
                }
                R.id.navigation_system -> {
                    binding.viewPage.setCurrentItem(1,true)
                }
                R.id.navigation_open_number -> {
                    binding.viewPage.setCurrentItem(2,true)
                }
                R.id.navigation_project -> {
                    binding.viewPage.setCurrentItem(3,true)
                }
            }
            false
        }
        binding.viewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })
    }

    override fun initOnclick() {

    }

    override fun initData() {

    }

    override fun getModelClass() = MainViewModel::class.java

    override fun getLayoutId() = R.layout.activity_main

}