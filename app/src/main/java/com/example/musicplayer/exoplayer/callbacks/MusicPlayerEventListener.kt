package com.example.musicplayer.exoplayer.callbacks

import android.net.wifi.rtt.CivicLocationKeys.STATE
import android.util.Log
import android.widget.Toast
import com.example.musicplayer.exoplayer.MusicService
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player

class MusicPlayerEventListener (
    private val musicService: MusicService
): Player.EventListener{
    override fun onPlayerError(error: ExoPlaybackException) {
        super.onPlayerError(error)
        Toast.makeText(musicService, "An unknown error occured.", Toast.LENGTH_SHORT).show()
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        super.onPlayerStateChanged(playWhenReady, playbackState)
            if (playbackState == Player.STATE_READY && !playWhenReady){
                musicService.stopForeground(false)
            }
    }
}