package com.zhao.wanandroid.ui.web

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.just.agentweb.*
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseActivity
import com.zhao.wanandroid.utils.ThemeColorUtil
import com.zhao.wanandroid.weight.webclient.WebClientFactory


class WebActivity : BaseActivity() {

    companion object {
        private const val ACTIVITY_URL = "webUrl"
        private const val ACTIVITY_TITLE = "webTitle"

        fun start(context: Context, title: String, url: String) {
            val intent = Intent(context, WebActivity::class.java)
            val bundle = Bundle()
            bundle.putString(ACTIVITY_TITLE,title)
            bundle.putString(ACTIVITY_URL, url)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    private lateinit var toolbar: Toolbar
    private lateinit var rootLayout: LinearLayout
    private lateinit var webView: AgentWebView
    private lateinit var mAgentWeb: AgentWeb
    private val webClient by lazy {
        object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        try {
            val bundle = intent.extras ?: throw Exception("bundle is null?")
            val url = bundle.getString(ACTIVITY_URL) ?: throw Exception("url is null?")
            toolbar = findViewById(R.id.toolbar)
            toolbar.setNavigationIcon(R.drawable.ic_back)
            rootLayout = findViewById(R.id.rootLayout)

            /*val webView = AgentWebView(this)
            mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(rootLayout, 1, LinearLayout.LayoutParams(-1,-1))
                .useDefaultIndicator(ThemeColorUtil.getThemeColor(this, R.attr.colorOnPrimary), 2)
                .setWebView(webView)
                .setWebViewClient(WebClientFactory.create(url))
                .setWebChromeClient(webChromeClient)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go(url)*/
            mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(rootLayout, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url)
        } catch (e: Exception) {
            showGeneralDialog(null, e.toString(), null)
        }
        /* mAgentWeb.webCreator.webView.apply {
             overScrollMode = WebView.OVER_SCROLL_NEVER
             settings.domStorageEnabled = true
             settings.javaScriptEnabled = true
             settings.loadsImagesAutomatically = true
             settings.useWideViewPort = true
             settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
         }*/
    }

    override fun initOnclick() {

    }

    override fun initData() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb.webLifeCycle?.onDestroy()
        super.onDestroy()
    }

    private val webChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            toolbar.title = title
        }
    }
}