package com.zhao.wanandroid.base.view_page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhao.wanandroid.utils.LogUtils

/**
 *创建时间： 2021/12/28
 *编   写：  zjf
 *页面功能:
 */
class BaseViewPageAdapter(lifecycle: Lifecycle, supportFragment: FragmentManager) : FragmentStateAdapter(supportFragment, lifecycle),
    BaseViewPageInterface {

    protected val fragmentList: MutableList<Pair<String, Fragment>> by lazy { ArrayList() }


    override fun getItemCount(): Int {
        if (fragmentList.isEmpty()){
            return 0
        }
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position].second
    }

    override fun addFragment(fragment: Fragment, title: String) {
        val pair = Pair(title, fragment)
        fragmentList.add(pair)
        notifyItemInserted(fragmentList.indexOf(pair))
    }

    override fun removeFragment(fragment: Fragment) {
        fragmentList.find { it.second == fragment }?.also {
            val index = fragmentList.indexOf(it)
            notifyItemRemoved(index)
            fragmentList.removeAt(index)
        }

    }

    override fun refreshFragments(data: List<Pair<String,Fragment>>) {
        if (fragmentList.isNotEmpty()) {
            fragmentList.clear()
            notifyItemRangeRemoved(0,itemCount)
        }
        fragmentList.addAll(data)
        notifyItemRangeInserted(0, fragmentList.size)
    }

    override fun getFragment(index: Int): Fragment {
        return fragmentList[index].second
    }

    override fun getFragmentTitle(index: Int): String {
        return fragmentList[index].first
    }


}