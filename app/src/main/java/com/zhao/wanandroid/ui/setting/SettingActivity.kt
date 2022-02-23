package com.zhao.wanandroid.ui.setting

import android.content.Intent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.databinding.ActivitySettingBinding
import com.zhao.wanandroid.local.SpName
import com.zhao.wanandroid.ui.web.WebActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BaseVmActivity<SettingViewModel, ActivitySettingBinding>() {

    companion object {
        fun start(context: AppCompatActivity) {
            val intent = Intent(context, SettingActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val adapter by lazy { SettingAdapter() }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    override fun initOnclick() {
        adapter.onItemClick { i, settingBean ->
            when (settingBean.type) {
                AppState.SettingType.NO_IMAGE -> {
                    viewModel.updateLocalSetting(SpName.Setting.IMAGE_KEY, settingBean.check ?: false)
                }
                AppState.SettingType.TOP_ARTICLE -> {
                    viewModel.updateLocalSetting(SpName.Setting.ARTICLE_TOP_KEY, settingBean.check ?: false)
                }
                AppState.SettingType.CUT_NIGHT -> {

                }
                AppState.SettingType.THEME_COLOR -> {

                }
                AppState.SettingType.CLEAR_CACHE -> {
                    viewModel.deleteCache()
                }
                AppState.SettingType.DOWNLOAD_CODE -> {

                }
                AppState.SettingType.APP_VERSION -> {

                }
                AppState.SettingType.OFFICIAL_WEB -> {
                    WebActivity.start(this,settingBean.details ?: "")
                }
                AppState.SettingType.DOWNLOAD_LOG -> {
                    WebActivity.start(this,settingBean.details ?: "")
                }
                AppState.SettingType.CODE -> {
                    WebActivity.start(this,settingBean.details ?: "")
                }
                AppState.SettingType.COPYRIGHT -> {
                    showGeneralDialog("版权声明","本APP所使用的所有的API均有玩Android网站提供，仅供学习交流，不可以用于商业用途",null)
                }
                AppState.SettingType.ABOUT_ME -> {

                }
            }
        }
    }

    override fun initData() {
        viewModel.initSetting()
    }

    override fun observe() {
        super.observe()
        viewModel.settingTypeList.observe({ lifecycle }) {
            adapter.refreshAllItem(it)
        }
    }

    override fun getModelClass(): Class<SettingViewModel> {
        return SettingViewModel::class.java
    }

    private fun showSelectThemeDialog(){
        val view = LayoutInflater.from(this).inflate(R.layout.base_recycler,null,false)
        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter
        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .show()
    }

}