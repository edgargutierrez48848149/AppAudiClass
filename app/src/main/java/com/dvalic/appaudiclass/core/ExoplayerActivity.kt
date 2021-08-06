package com.dvalic.appaudiclass.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.ActivityExoplayerBinding
import com.dvalic.appaudiclass.databinding.ActivityPdfViewerBinding

class ExoplayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExoplayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExoplayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}