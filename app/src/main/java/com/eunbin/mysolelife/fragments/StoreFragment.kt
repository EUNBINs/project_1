package com.eunbin.mysolelife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.eunbin.mysolelife.R
import kotlinx.android.synthetic.main.activity_content_show.*

class StoreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // fragment_store로 넘어오면 웹뷰로 연결해주는 코드
        val view = inflater.inflate(R.layout.fragment_store, container, false)


        val webView : WebView = view.findViewById(R.id.storeWebView)
        webView.loadUrl("https://www.naver.com/")

        return view
    }

}