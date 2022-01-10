package com.zhao.wanandroid.base

import android.os.Bundle
import android.view.Window
import androidx.annotation.LayoutRes
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
abstract class BaseActivity : AppCompatActivity(), BaseLoadingInterface {

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
        setContentView(getLayoutId())
        initView()
        initOnclick()
        initData()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initOnclick()

    abstract fun initData()

    override fun isShowLoadingDialog(isShow: Boolean) {
        if (isShow) {
            showLoadingDialog()
        } else {
            hideLoadingDialog()
        }
    }

    override fun isShowMsgDialog(content: String) {
        showGeneralDialog(null,content,null)
    }

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