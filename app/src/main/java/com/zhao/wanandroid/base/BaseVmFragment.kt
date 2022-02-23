package com.zhao.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.zhao.wanandroid.base.fragment.BaseFragment

/**
 *创建时间： 2021/12/6
 *编   写：  zjf
 *页面功能:
 */

abstract class BaseVmFragment<VM : BaseViewModel, VB : ViewDataBinding> : BaseFragment() {

    protected lateinit var binding: VB

    protected lateinit var result: ActivityResultLauncher<Array<String>>

    protected val viewModel: VM by lazy { ViewModelProvider(requireActivity())[getModelClass()] }

    abstract fun getModelClass(): Class<VM>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), null, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun initCreated() {
        initView()
        initClick()
        observer()
        initData()
    }

    protected open fun initClick() {

    }

    open fun observer() {
        viewModel.isShowLoading.observe({ lifecycle }) {
            (requireActivity() as BaseLoadingInterface).isShowLoadingDialog(it)
        }
        viewModel.showMsg.observe({lifecycle}){
            (requireActivity() as BaseLoadingInterface).isShowMsgDialog(it)
        }
    }

}