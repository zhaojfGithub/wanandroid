package com.zhao.wanandroid.ui.main.activity

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.base.adapter.business.RecyclerMoveInterface
import com.zhao.wanandroid.databinding.ActivityMainBinding
import com.zhao.wanandroid.databinding.DrawerHeaderBinding
import com.zhao.wanandroid.ui.main.fragment.project.ProjectFragment
import com.zhao.wanandroid.ui.main.fragment.system.SystemFragment
import com.zhao.wanandroid.ui.main.fragment.home.HomeFragment
import com.zhao.wanandroid.ui.main.fragment.open_number.OpenNumberFragment
import com.zhao.wanandroid.ui.main.fragment.plaza.PlazaFragment
import com.zhao.wanandroid.ui.search.SearchActivity
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
        binding.include.toolbar.setNavigationIcon(R.drawable.ic_density_medium)
        setSupportActionBar(binding.include.toolbar)
        binding.navigationView.addHeaderView(drawerHeaderBinder.root)
        val fragments = listOf(
            HomeFragment.newInstance(),
            PlazaFragment.newInstance(),
            OpenNumberFragment.newInstance(),
            SystemFragment.newInstance(),
            ProjectFragment.newInstance()
        )
        val adapter = MainViewPageAdapter(lifecycle, supportFragmentManager, fragments)
        binding.viewPage.adapter = adapter
        binding.viewPage.offscreenPageLimit = 4
        binding.viewPage.isUserInputEnabled = false
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    binding.viewPage.setCurrentItem(0, true)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_plaza -> {
                    binding.viewPage.setCurrentItem(1, true)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_open_number -> {
                    binding.viewPage.setCurrentItem(2, true)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_nv -> {
                    binding.viewPage.setCurrentItem(3, true)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_project -> {
                    binding.viewPage.setCurrentItem(4, true)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
        binding.floatingActionButton.setOnClickListener {
            val index = binding.viewPage.currentItem
            val fragment : RecyclerMoveInterface = fragments[index]
            fragment.moveHeader()
        }
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
                SearchActivity.start(this)
            }
            R.id.toolbarAdd -> {

            }
        }
        return true

    }
}