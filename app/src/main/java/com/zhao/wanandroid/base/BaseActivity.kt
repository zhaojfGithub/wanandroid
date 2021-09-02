package com.zhao.wanandroid.base

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.zhao.wanandroid.R
import com.zhao.wanandroid.utils.LogUtils
import com.zhao.wanandroid.weight.GeneralDialog

/**
 *创建时间： 2021/9/1
 *编   写：  zjf
 *页面功能:
 */
abstract class BaseActivity : AppCompatActivity() {

    private val loadingDialog: AlertDialog by lazy {
        AlertDialog.Builder(this).setView(R.layout.dialog_loading).create()
    }

    private val generalDialog: GeneralDialog by lazy { GeneralDialog(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCreate(savedInstanceState)
    }

    open fun initCreate(savedInstanceState: Bundle?) {
        initView()
        initOnclick()
        initData()
    }

    abstract fun initView()

    abstract fun initOnclick()

    abstract fun initData()

    protected fun showLoadingDialog() {
        hideLoadingDialog()
        loadingDialog.show()
    }

    protected fun hideLoadingDialog() {
        if (loadingDialog.isShowing) {
            loadingDialog.hide()
        }
    }

    private fun dismissLoadingDialog() {
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

    protected fun showGeneralDialog() {
        hideGeneralDialog()
        generalDialog.setDialogSetting(null, "这是一条提示",{
            LogUtils.e("点击确定")
        }){
            LogUtils.e("点击取消")
        }
    }

    protected fun hideGeneralDialog() {
        if (generalDialog.isShowing) {
            generalDialog.hide()
        }
    }

    private fun dismissGeneralDialog() {
        if (generalDialog.isShowing) {
            generalDialog.dismiss()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        dismissLoadingDialog()
    }
}