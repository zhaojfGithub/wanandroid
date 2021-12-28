package com.zhao.wanandroid.base.view_page

import androidx.fragment.app.Fragment

/**
 *创建时间： 2021/12/28
 *编   写：  zjf
 *页面功能:
 */
interface BaseViewPageInterface {
    /**
     * 添加一个Fragment
     */
    fun addFragment(fragment: Fragment, title: String)

    /**
     * 删除一个fragment
     */
    fun removeFragment(fragment: Fragment)

    /**
     * 刷新当前所有的fragment
     */
    fun refreshFragments(data: List<Pair<String,Fragment>>)

    /**
     * 获取一个fragment
     */
    fun getFragment(index: Int): Fragment

    /**
     * 获取fragment对应的主题
     */
    fun getFragmentTitle(index: Int): String
}