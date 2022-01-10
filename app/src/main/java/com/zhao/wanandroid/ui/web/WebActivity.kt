package com.zhao.wanandroid.ui.web

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseActivity
import com.zhao.wanandroid.utils.LogUtils

@SuppressLint("SetJavaScriptEnabled")
class WebActivity : BaseActivity() {

    companion object {
        private const val ACTIVITY_URL = "webUrl"
        private const val ACTIVITY_TITLE = "webTitle"

        fun start(context: Context, url: String, title: String) {
            val intent = Intent(context, WebActivity::class.java)
            val bundle = Bundle()
            bundle.putString(ACTIVITY_URL, url)
            bundle.putString(ACTIVITY_TITLE, title)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    private lateinit var toolbar: Toolbar
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }


    override fun initView() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
        webView.clearCache(true)
        webView.clearHistory()
        webView.settings.apply {
            domStorageEnabled = true
            javaScriptEnabled = true
            loadsImagesAutomatically = true
            useWideViewPort = true
            loadWithOverviewMode = true
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        //webView.webViewClient = MyWebViewClient()
        //webView.webChromeClient = MyWebChromeClient()
        try {
            val bundle = intent.extras ?: throw Exception("bundle is null?")
            val url = bundle.getString(ACTIVITY_URL) ?: throw Exception("url is null?")
            val title = bundle.getString(ACTIVITY_TITLE) ?: throw Exception("title is null")
            toolbar.title = title
            webView.loadUrl(url)
            LogUtils.e("initData$url")
        }catch (e:Exception){
            showGeneralDialog(null,e.toString(),null)
        }
    }

    override fun initOnclick() {

    }

    override fun initData() {


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
            }
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        webView.settings.javaScriptEnabled = true
    }

    override fun onStop() {
        super.onStop()
        webView.settings.javaScriptEnabled = false
    }

    private class MyWebViewClient : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            LogUtils.e("onStart:$url")
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            LogUtils.e(url.toString())
            super.onPageFinished(view, url)
        }

        override fun onLoadResource(view: WebView?, url: String?) {
            super.onLoadResource(view, url)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            LogUtils.e("onStart"+error.toString())
            super.onReceivedError(view, request, error)
        }

        override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
            super.onReceivedHttpError(view, request, errorResponse)
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            handler?.proceed()
        }

        override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
            return super.shouldOverrideKeyEvent(view, event)
        }

        override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
            super.onScaleChanged(view, oldScale, newScale)
        }

        override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
            return super.shouldInterceptRequest(view, request)
        }
    }

    private class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            LogUtils.e("onProgressChanged$newProgress")
            super.onProgressChanged(view, newProgress)
        }

        override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
            super.onReceivedIcon(view, icon)
        }

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
        }

        override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
            return super.onJsAlert(view, url, message, result)
        }

        override fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?, result: JsPromptResult?): Boolean {
            return super.onJsPrompt(view, url, message, defaultValue, result)
        }

        override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
            return super.onJsConfirm(view, url, message, result)
        }

        override fun onPermissionRequest(request: PermissionRequest?) {
            super.onPermissionRequest(request)
        }

        override fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>?, fileChooserParams: FileChooserParams?): Boolean {
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
        }

        override fun getVideoLoadingProgressView(): View? {
            return super.getVideoLoadingProgressView()
        }

        override fun getDefaultVideoPoster(): Bitmap? {
            return super.getDefaultVideoPoster()
        }

        override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
            super.onShowCustomView(view, callback)
        }

        override fun onHideCustomView() {
            super.onHideCustomView()
        }
    }

}