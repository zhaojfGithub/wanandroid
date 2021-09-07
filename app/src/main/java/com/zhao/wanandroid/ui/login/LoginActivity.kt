package com.zhao.wanandroid.ui.login

import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.databinding.ActivityLoginBinding
import com.zhao.wanandroid.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseVmActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun initView() {

    }

    override fun initOnclick() {

        }

    override fun initData() {
        viewModel.getRepository()
    }

    override fun observe() {
        super.observe()
        binding.data = viewModel
        viewModel.apply {
            showChaptersData.observe({lifecycle}){
                LogUtils.e(it)
            }

        }
    }

    override fun getModelClass() = LoginViewModel::class.java

    override fun getLayoutId() = R.layout.activity_login
}