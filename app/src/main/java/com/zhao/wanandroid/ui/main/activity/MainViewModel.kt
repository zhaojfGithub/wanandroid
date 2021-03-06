package com.zhao.wanandroid.ui.main.activity

import androidx.hilt.lifecycle.ViewModelInject
import com.zhao.wanandroid.base.BaseViewModel
import com.zhao.wanandroid.common.launch
import com.zhao.wanandroid.utils.LogUtils

/**
 *创建时间： 2021/9/24
 *编   写：  zjf
 *页面功能:
 */
class MainViewModel @ViewModelInject constructor(private val repository: MainRepository) : BaseViewModel() {

    companion object {
        const val name1 = "name1"
        const val name2 = "name2"
        const val name3 = "name3"
        const val name4 = "name4"
        const val name5 = "name5"
    }

    fun test()=  launch({
         val a = repository.test()
        LogUtils.e(a)
    },{
      throw it
    },{

    })

}