package com.zhao.wanandroid.weight.webclient

import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.webkit.*
import androidx.annotation.RequiresApi
import com.just.agentweb.WebViewClient
import com.zhao.wanandroid.utils.LogUtils

/**
 *创建时间： 2022/1/11
 *编   写：  zjf
 *页面功能:
 */
open class BaseWebClient : WebViewClient() {
    protected val TAG = "BaseWebClient"

    // 拦截的网址
    private val blackHostList = arrayListOf(
        "www.taobao.com",
        "www.jd.com",
        "yun.tuisnake.com",
        "yun.lvehaisen.com",
        "yun.tuitiger.com"
    )

    private fun isBlackHost(host: String): Boolean {
        for (blackHost in blackHostList) {
            if (blackHost == host) {
                return true
            }
        }
        return false
    }

    private fun shouldInterceptRequest(uri: Uri?): Boolean {
        if (uri != null) {
            val host = uri.host ?: ""
            return isBlackHost(host)
        }
        return false
    }

    private fun shouldOverrideUrlLoading(uri: Uri?): Boolean {
        if (uri != null) {
            val host = uri.host ?: ""
            return isBlackHost(host)
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
        if (shouldInterceptRequest(request?.url)) {
            return WebResourceResponse(null, null, null)
        }
        return super.shouldInterceptRequest(view, request)
    }

    override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
        LogUtils.e(TAG, "shouldInterceptRequest: $url")
        if (shouldInterceptRequest(Uri.parse(url))) {
            return WebResourceResponse(null, null, null)
        }
        return super.shouldInterceptRequest(view, url)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return shouldOverrideUrlLoading(Uri.parse(url))
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return shouldOverrideUrlLoading(request?.url)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        LogUtils.e(TAG, "onPageStarted: $url")
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        LogUtils.e(TAG, "onPageFinished: $url")
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        // super.onReceivedSslError(view, handler, error)
        handler?.proceed()
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        LogUtils.e(TAG, "onReceivedError: $error")
        super.onReceivedError(view, request, error)
    }
}