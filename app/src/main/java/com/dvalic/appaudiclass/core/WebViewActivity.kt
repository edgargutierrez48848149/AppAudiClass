package com.dvalic.appaudiclass.core

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    private var Ruta: String? = ""
    private var NombreApp: String? = ""
    private var Anio: String? = ""
    private var Version: String? = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let { bundle ->
            Ruta = bundle.getString("Ruta")
            NombreApp = bundle.getString("NombreApp")
            Anio = bundle.getString("Anio")
            Version = bundle.getString("Version")
        }

        if (Anio == null || Anio.equals("") || NombreApp == null || NombreApp.equals("")) {
            binding.btnBuy.visibility = View.GONE
        }

        binding.WebView.webChromeClient = object : WebChromeClient() {
        }

        binding.WebView.webViewClient = object : WebViewClient() {

        }
        val settings = binding.WebView.settings
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.mediaPlaybackRequiresUserGesture = false
        Ruta?.let { binding.WebView.loadUrl(it) }
        binding.btnBuy.setOnClickListener {
            val intent = Intent()
            intent.putExtra("NombreApp", NombreApp)
            intent.putExtra("Anio", Anio)
            intent.putExtra("Version", Version)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onBackPressed() {
        if (binding.WebView.canGoBack()) {
            binding.WebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}