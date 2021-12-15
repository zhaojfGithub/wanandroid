package com.zhao.wanandroid.base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.zhao.wanandroid.R
import com.zhao.wanandroid.weight.GeneralDialog

/**
 *创建时间： 2021/9/1
 *编   写：  zjf
 *页面功能:
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val TAG = this.javaClass.simpleName

    private val loadingDialog: AlertDialog by lazy {
        AlertDialog.Builder(this).setView(R.layout.dialog_loading).create()
    }

    private val generalDialog: GeneralDialog by lazy { GeneralDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
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

    protected fun showGeneralDialog(title: String?, msg: String, onResult: ((Boolean) -> Unit)?) {
        hideGeneralDialog()
        onResult?.also { onClick ->
            generalDialog.setDialogSetting(title, msg, {
                onClick(true)
            }) {
                onClick(false)
            }
        } ?: run {
            generalDialog.setDialogSetting(title, msg, null, null)
        }

        generalDialog.show()
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