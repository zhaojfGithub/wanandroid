package com.zhao.wanandroid.ui.main.fragment.open_number

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhao.wanandroid.bean.OpenNumberBoxBean

/**
 *创建时间： 2021/12/21
 *编   写：  zjf
 *页面功能:
 */
class OpenNumberViewPageAdapter(lifecycle: Lifecycle, supportFragment: FragmentManager) :
    FragmentStateAdapter(supportFragment, lifecycle) {

    private val fragmentList: MutableList<Fragment> by lazy { ArrayList() }
    private val fragmentTitle: MutableList<String> by lazy { ArrayList() }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addAllData(data: List<OpenNumberBoxBean>) {
        if (fragmentList.isNotEmpty()) {
            fragmentList.clear()
        }
        if (fragmentTitle.isNotEmpty()) {
            fragmentTitle.clear()
        }
        data.forEach {
            fragmentList.add(OpenNumberItemFragment.newInstance(it.id))
            fragmentTitle.add(it.name)
        }
        notifyItemRangeChanged(0, this.fragmentList.size)
    }

    fun getTitle(index: Int) = fragmentTitle[index]

    fun getFragment(index: Int) = fragmentList[index]
}