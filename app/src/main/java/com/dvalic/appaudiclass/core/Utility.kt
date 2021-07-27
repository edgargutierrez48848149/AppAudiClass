package com.dvalic.appaudiclass.core

import android.app.Application
import android.content.Intent
import android.net.Uri

class Utility: Application() {

    fun showWebPage(url: String){
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}