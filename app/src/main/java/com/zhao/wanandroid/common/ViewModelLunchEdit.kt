package com.zhao.wanandroid.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 *创建时间： 2021/9/17
 *编   写：  zjf
 *页面功能:
 */
fun ViewModel.launch(
    block: suspend CoroutineScope.() -> Unit,
    onError: (e: Throwable) -> Unit = {},
    onComplete: () -> Unit = {}
) {
    viewModelScope.launch {
        try {
            block.invoke(this)
        } catch (e: Exception) {
            onError(e)
        } finally {
            onComplete()
        }
    }
}