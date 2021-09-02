package com.zhao.wanandroid.ui.login

import androidx.lifecycle.lifecycleScope
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.databinding.ActivityLoginBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : BaseVmActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun initView() {

    }

    override fun initOnclick() {
        binding.btSure.setOnClickListener {
            showGeneralDialog()
        }
        binding.btCancel.setOnClickListener {
            viewModel.isShowLoading.value = false
        }
    }

    override fun initData() {

    }

    override fun observe() {
        super.observe()
        binding.data = viewModel
    }

    override fun getModelClass() = LoginViewModel::class.java

    override fun getLayoutId() = R.layout.activity_login
}