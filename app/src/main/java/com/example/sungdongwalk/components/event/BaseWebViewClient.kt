package com.example.sungdongwalk.components.event

import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.example.sungdongwalk.viewmodels.EventViewModel
import com.google.accompanist.web.AccompanistWebViewClient

class BaseWebViewClient : AccompanistWebViewClient(){
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        EventViewModel.instance.updateUrl(request?.url.toString())
        return super.shouldOverrideUrlLoading(view, request)
    }
}