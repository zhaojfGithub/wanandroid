package com.zhao.wanandroid.ui.main.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *创建时间： 2021/12/6
 *编   写：  zjf
 *页面功能:
 */
class MainViewPageAdapter(lifecycle: Lifecycle, supportFragmentManager: FragmentManager, private val fragments: List<Fragment>) :
    FragmentStateAdapter(supportFragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}