package com.dvalic.appaudiclass.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.ActivitySplashBinding
import com.dvalic.appaudiclass.ui.main.MainActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.upstream.RawResourceDataSource


class SplashActivity : AppCompatActivity(), Player.Listener {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pvSplash.useController = false
        initializePlayer()
    }

    private fun initializePlayer() {
        val player: SimpleExoPlayer? = SimpleExoPlayer.Builder(this).build()
        binding.pvSplash.player = player
        val uri: Uri = RawResourceDataSource.buildRawResourceUri(R.raw.splash)
        val mediaItem = MediaItem.fromUri(uri)
        player?.addMediaItem(mediaItem)
        player?.prepare()
        player?.play()
        binding.pvSplash.player?.addListener(this)
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)
        if (!isPlaying){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}