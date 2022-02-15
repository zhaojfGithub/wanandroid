package com.zhao.wanandroid.ui.main.activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.base.adapter.business.RecyclerMoveInterface
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.ActivityMainBinding
import com.zhao.wanandroid.databinding.DrawerHeaderBinding
import com.zhao.wanandroid.ui.common.CommonActivity
import com.zhao.wanandroid.ui.main.fragment.home.HomeFragment
import com.zhao.wanandroid.ui.main.fragment.open_number.OpenNumberFragment
import com.zhao.wanandroid.ui.main.fragment.plaza.PlazaFragment
import com.zhao.wanandroid.ui.main.fragment.project.ProjectFragment
import com.zhao.wanandroid.ui.main.fragment.system.SystemFragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
    }

    private fun initSplashScreen() {
        val splashScreen = installSplashScreen()
        //不知道为什么添加到动画的时候显示这个在之前添加
        /*splashScreen.setOnExitAnimationListener { splashScreenView ->
            //val animationDuration = splashScreenView.iconAnimationDurationMillis
            //val animationStart = splashScreenView.iconAnimationDurationMillis
            //val remainingDuration = (
            //        animationDuration - (SystemClock.uptimeMillis() - animationStart)
            //        ).coerceAtLeast(0L)
            val slideUp = ObjectAnimator.ofFloat(splashScreenView.iconView, View.TRANSLATION_Y, 0F, -splashScreenView.iconView.height.toFloat())
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 300L
            slideUp.doOnEnd { splashScreenView.remove() }
            slideUp.start()
        }*/
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
            val fragment: RecyclerMoveInterface = fragments[index]
            fragment.moveHeader()
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.drawerIntegral -> {
                    CommonActivity.start(this, AppState.CommonState.INTEGRAL)
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
                CommonActivity.start(this, AppState.CommonState.SHARE)
            }
        }
        return true

    }
}