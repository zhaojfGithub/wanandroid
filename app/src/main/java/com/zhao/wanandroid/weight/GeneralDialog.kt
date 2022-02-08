package com.zhao.wanandroid.weight

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.R
import com.zhao.wanandroid.databinding.DialogGeneraBinding

/**
 *创建时间： 2021/9/2
 *编   写：  zjf
 *页面功能:
 */
class GeneralDialog(context: Context) : AlertDialog(context) {

    var title: String = MyApplication.getInstance().getString(R.string.hint)
    var onClickCancel: ((Boolean) -> Unit)? = null
    var body: String? = null
    var onClickSure: ((Boolean) -> Unit)? = null
    var binding : DialogGeneraBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_genera, null, false)
        setContentView(binding!!.root)
        binding!!.data = this
    }

    fun setDialogSetting(title: String?, body: String?, onClickSure: ((Boolean) -> Unit)?, onClickCancel: ((Boolean) -> Unit)?) {
        title?.let { this.title = it }
        body?.let { this.body = it }
        onClickSure?.let { this.onClickSure = it }
        onClickCancel?.let { this.onClickCancel = it }
        show()
    }

    fun btGeneraDialogCancelOnclick(){
        onClickCancel?.let { it(true) }
        hide()
    }

    fun btGeneraDialogSureOnclick(){
        onClickSure?.let { it(false) }
        hide()
    }
}