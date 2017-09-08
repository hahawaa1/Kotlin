package com.flyinbed.net.ui.activity

import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.cdd.cdb.ui.base.BaseActivity
import com.flyinbed.net.R
import kotlinx.android.synthetic.main.activity_web.*

/**
 * 作者：Administrator on 2017/9/7 17:28
 * 邮箱：zhanghuaiha@gmail.com
 */
class WebActivity : BaseActivity() {
    var settings : WebSettings? = null

    override fun provideContentViewId(): Int {
        return R.layout.activity_web
    }

    override fun initView() {
        settings = web.settings
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        settings?.javaScriptEnabled = true
    }

    override fun initData() {
        val url = intent.getStringExtra("url")
        web.loadUrl(url)
        web.setWebViewClient(object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url)
                return true
            }
        })
    }
}