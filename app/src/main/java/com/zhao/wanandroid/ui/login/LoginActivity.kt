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
        binding.btRegister.setOnClickListener {
            viewModel.updateType(1)
        }
        binding.btLogin.setOnClickListener {
            if (viewModel.type.value!! == 0){
                //去登陆
                val userName = binding.editUsername.text.toString()
                val passWord = binding.editPassword.text.toString()
                if (userName.isBlank() && passWord.isBlank()){
                    showGeneralDialog(null,"账号或者密码为NULL",null)
                }else{
                    viewModel.login(userName,passWord)
                }
            }else{
                //去注册
            }
        }
    }

    override fun initData() {}

    override fun observe() {
        super.observe()
        binding.data = viewModel
        viewModel.apply {

        }
    }

    override fun getModelClass() = LoginViewModel::class.java

    override fun getLayoutId() = R.layout.activity_login
}