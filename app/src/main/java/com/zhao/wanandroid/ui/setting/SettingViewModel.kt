package com.zhao.wanandroid.ui.setting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.zhao.wanandroid.BuildConfig
import com.zhao.wanandroid.MyApplication
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.bean.SettingBean
import com.zhao.wanandroid.common.AppState
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.local.SpName
import com.zhao.wanandroid.utils.ExceptionUtil
import com.zhao.wanandroid.utils.getSpValue
import com.zhao.wanandroid.utils.putSpValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream

/**
 *创建时间： 2022/2/21
 *编   写：  zjf
 *页面功能:
 */
class SettingViewModel @ViewModelInject constructor(val repository: SettingRepository) : BaseViewModel() {

    val settingTypeList = MutableLiveData<List<SettingBean>>()

    fun initSetting() = launch({
        isShowLoading.value = true
        val cacheSize: Float = getCacheSize() / 1024F / 1024F / 8F
        settingTypeList.value = arrayListOf(
            SettingBean(title = "基本设置"),
            SettingBean(AppState.SettingType.NO_IMAGE,
                "无图模式",
                SpName.Setting.IMAGE_KEY,
                "开启后在非wifi环境下不加载图片",
                AppState.SettingItemState.CHECK,
                getSpValue(SpName.Setting.IMAGE_KEY, false)),
            SettingBean(AppState.SettingType.TOP_ARTICLE, "是否显示首页置顶文章",
                SpName.Setting.ARTICLE_TOP_KEY,
                "开始后将不会显示首页置顶文章",
                AppState.SettingItemState.CHECK,
                getSpValue(SpName.Setting.ARTICLE_TOP_KEY, false)),
            SettingBean(AppState.SettingType.CUT_NIGHT, "自动切换夜间模式", null, "设置切换的时间", AppState.SettingItemState.NONE, null),
            SettingBean(title = "其他设置"),
            SettingBean(AppState.SettingType.THEME_COLOR,
                "主题颜色",
                SpName.Setting.THEME_KEY,
                "自定义主题颜色",
                AppState.SettingItemState.COLOR,
                getSpValue(SpName.Setting.ARTICLE_TOP_KEY, false)),
            SettingBean(AppState.SettingType.CLEAR_CACHE, "清除缓存", null, "${cacheSize.toInt()}M"),
            SettingBean(AppState.SettingType.DOWNLOAD_CODE, "扫码下载", null, "好用的话推荐给你的小伙伴吧"),
            SettingBean(title = "关于"),
            SettingBean(AppState.SettingType.APP_VERSION, "版本", null, BuildConfig.VERSION_NAME),
            SettingBean(AppState.SettingType.OFFICIAL_WEB, "官方网站", null, "https://www.wanandroid.com"),
            SettingBean(AppState.SettingType.DOWNLOAD_LOG, "更新日志", null, "https://github.com/zhaojfGithub/wanandroid/releases"),
            SettingBean(AppState.SettingType.CODE, "源代码", null, "https://github.com/zhaojfGithub/wanandroid"),
            SettingBean(AppState.SettingType.COPYRIGHT, "版权声明", null, "仅作个人及非商业用途"),
            SettingBean(AppState.SettingType.ABOUT_ME, "关于我们", null, "")
        )
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun <T> updateLocalSetting(key: String, value: T) = launch({
        isShowLoading.value = true
        putSpValue(key, value)
    }, {
        showMsg.value = ExceptionUtil.catchException(it)
    }, {
        isShowLoading.value = false
    })

    fun deleteCache(file: File = MyApplication.getInstance().cacheDir) {
        launch({
            isShowLoading.value = true
            withContext(Dispatchers.IO) {
                if (file.exists()) {
                    file.listFiles()?.forEach {
                        if (it.isFile) {
                            it.delete()
                        } else if (it.isDirectory) {
                            deleteCache(file = it)
                        }
                    }
                }
            }
        }, {
            showMsg.value = ExceptionUtil.catchException(it)
        }, {
            isShowLoading.value = false
            showMsg.value = MyApplication.getInstance().getString(R.string.clear_success)
        })
    }

    private suspend fun getCacheSize(file: File = MyApplication.getInstance().cacheDir): Int = withContext(Dispatchers.IO) {
        var size = 0
        if (file.exists()) {
            try {
                if (file.isFile) {
                    val fileInputStream = FileInputStream(file)
                    size += fileInputStream.available()
                    fileInputStream.close()
                } else if (file.isDirectory) {
                    file.listFiles()?.forEach {
                        size += getCacheSize(it)
                    }
                }
            } catch (e: Exception) {
                showMsg.value = ExceptionUtil.catchException(e)
            }
        }
        size
    }
}