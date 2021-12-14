package com.zhao.wanandroid.ui.main.activity

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.databinding.ActivityMainBinding
import com.zhao.wanandroid.databinding.DrawerHeaderBinding
import com.zhao.wanandroid.ui.main.fragment.*
import com.zhao.wanandroid.ui.main.fragment.home.HomeFragment
import com.zhao.wanandroid.ui.main.fragment.home.HomeViewModel
import com.zhao.wanandroid.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseVmActivity<MainViewModel, ActivityMainBinding>() {

    private val drawerHeaderBinder: DrawerHeaderBinding by lazy { DataBindingUtil.inflate(layoutInflater, R.layout.drawer_header, null, false) }

    companion object {
        fun start(context: AppCompatActivity) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_density_medium)
        setSupportActionBar(binding.toolbar)
        binding.navigationView.addHeaderView(drawerHeaderBinder.root)
        val fragments = listOf(
            HomeFragment.newInstance(),
            SystemFragment.newInstance(),
            OpenNumberFragment.newInstance(),
            NavigationFragment.newInstance(),
            ProjectFragment.newInstance()
        )
        val adapter = MainViewPageAdapter(lifecycle, supportFragmentManager, fragments)
        binding.viewPage.adapter = adapter
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    binding.viewPage.setCurrentItem(0, true)
                }
                R.id.navigation_system -> {
                    binding.viewPage.setCurrentItem(1, true)
                }
                R.id.navigation_open_number -> {
                    binding.viewPage.setCurrentItem(2, true)
                }
                R.id.navigation_nv -> {
                    binding.viewPage.setCurrentItem(3, true)
                }
                R.id.navigation_project -> {
                    binding.viewPage.setCurrentItem(4, true)
                }
            }
            false
        }
        binding.viewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.drawerIntegral -> {

                }
                R.id.drawerCollect -> {

                }
                R.id.drawerTodo -> {

                }
                R.id.drawerNight -> {

                }
                R.id.drawerSetting -> {

                }
                R.id.drawerMy -> {

                }
                R.id.drawerExit -> {

                }
            }
            true
        }
    }

    override fun initOnclick() {

    }

    override fun initData() {

    }

    override fun getModelClass() = MainViewModel::class.java

    override fun getLayoutId() = R.layout.activity_main

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(binding.navigationView)
            }
            R.id.toolbarSearch -> {
                LogUtils.e("toolbarSearch")
            }
        }
        return true
    }

}