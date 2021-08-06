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
    private var ruta: String? = ""
    private var nombreApp: String? = ""
    private var anio: String? = ""
    private var version: String? = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let { bundle ->
            ruta = bundle.getString("Ruta")
            nombreApp = bundle.getString("NombreApp")
            anio = bundle.getString("Anio")
            version = bundle.getString("Version")
        }

        if (anio.equals("") && anio.equals("") && nombreApp.equals("") && nombreApp.equals("")) {
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
        ruta?.let { binding.WebView.loadUrl(it) }
        binding.btnBuy.setOnClickListener {
            val intent = Intent()
            intent.putExtra("NombreApp", nombreApp)
            intent.putExtra("Anio", anio)
            intent.putExtra("Version", version)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onBackPressed() {
        if (binding.WebView.canGoBack()) {
            binding.WebView.goBack()
        } else {
            super.onBackPressed()
            finish()
        }
    }
}