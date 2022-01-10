package com.zhao.wanandroid.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.databinding.ActivityLoginBinding
import com.zhao.wanandroid.ui.main.activity.MainActivity
import com.zhao.wanandroid.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseVmActivity<LoginViewModel, ActivityLoginBinding>() {

    companion object {
        private const val ACTIVITY_TYPE = "activity_type"
        fun start(context: AppCompatActivity, type: Int = 0) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra(ACTIVITY_TYPE, type)
            context.startActivity(intent)
        }
    }

    override fun initView() {

    }

    override fun initOnclick() {
        binding.btRegister.setOnClickListener {
            start(this, type = 1)
        }
        binding.btLogin.setOnClickListener {
            LogUtils.e("22222")
            if (viewModel.type.value!! == 0) {
                //去登陆
                val userName = binding.editUsername.text.toString()
                val passWord = binding.editPassword.text.toString()
                if (userName.isBlank() && passWord.isBlank()) {
                    showGeneralDialog(null, "账号或者密码为NULL", null)
                } else {
                    viewModel.login(userName, passWord)
                }
            } else {
                //去注册

            }
        }
        binding.ivLogo.setOnClickListener {
            LogUtils.e("33333")
        }
        binding.root
    }

    override fun initData() {
        val type = intent.getIntExtra(ACTIVITY_TYPE, 0)
        viewModel.updateType(type)
        viewModel.isLogin()
    }

    override fun observe() {
        super.observe()
        binding.data = viewModel
        viewModel.apply {
            isLogin.observe({ lifecycle }) {
                if (it) {
                    MainActivity.start(this@LoginActivity)
                    finish()
                }
            }
            submitType.observe({ lifecycle }) {
                when (it) {
                    0 -> {
                        MainActivity.start(this@LoginActivity)
                    }
                    1 -> {
                        viewModel.updateType(0)
                    }
                    else -> {
                        LogUtils.e(TAG, "type设置错误")
                    }
                }
            }
        }
    }

    override fun getModelClass() = LoginViewModel::class.java

    override fun getLayoutId() = R.layout.activity_login
}

