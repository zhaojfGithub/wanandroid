package com.zhao.wanandroid.ui

import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseActivity
import com.zhao.wanandroid.utils.LogUtils

/**
 *创建时间： 2021/12/21
 *编   写：  zjf
 *页面功能:
 */
class TestActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
        val editText: SearchTipsEditText = findViewById(R.id.editText)
        editText.setData(listOf("123456789","23456789","3456789","456789","56789","6789","789"))
        editText.setSuccessListener {
            LogUtils.e(it.toString())
        }
        editText.setErrorListener {
            LogUtils.e(it)
        }
    }

    override fun initOnclick() {

    }

    override fun initData() {

    }
}