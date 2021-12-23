package com.zhao.wanandroid.base

import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 *创建时间： 2021/9/1
 *编   写：  zjf
 *页面功能:
 */
abstract class BaseVmActivity<VM: BaseViewModel, VB: ViewDataBinding> : BaseActivity() {

    protected lateinit var binding : VB

    protected lateinit var result: ActivityResultLauncher<Array<String>>

    protected val viewModel : VM by lazy { ViewModelProvider(this).get(getModelClass()) }

    abstract fun getModelClass(): Class<VM>

    open fun observe(){
        viewModel.apply {
            isShowLoading.observe({lifecycle}){
                if (it) showLoadingDialog() else hideLoadingDialog()
            }
            showMsg.observe({lifecycle}){
                showGeneralDialog(null,it,null)
            }
        }
    }

    override fun initCreate(savedInstanceState: Bundle?) {
        initViewDataBinding()
        initView()
        initOnclick()
        observe()
        initData()
    }


    private fun initViewDataBinding(){
        binding = DataBindingUtil.setContentView(this,getLayoutId()) as VB
        binding.lifecycleOwner = this
    }



}