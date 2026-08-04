package com.modify.music.service

import android.content.Intent
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ModifyMusicService : MediaSessionService() {

    @Inject
    lateinit var exoPlayer: ExoPlayer

    private var mediaSession: MediaSession? = null
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _currentSongId = MutableStateFlow<String?>(null)
    val currentSongId: StateFlow<String?> = _currentSongId.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    override fun onCreate() {
        super.onCreate()
        
        mediaSession = MediaSession.Builder(this, exoPlayer)
            .setCallback(object : MediaSession.Callback {
                override fun onPlay(player: Player) {
                    super.onPlay(player)
                    _isPlaying.value = true
                }

                override fun onPause(player: Player) {
                    super.onPause(player)
                    _isPlaying.value = false
                }

                override fun onSkipToNext(player: Player) {
                    super.onSkipToNext(player)
                }

                override fun onSkipToPrevious(player: Player) {
                    super.onSkipToPrevious(player)
                }

                override fun onSeekTo(player: Player, positionMs: Long) {
                    super.onSeekTo(player, positionMs)
                }
            })
            .build()
    }

    fun playSong(songId: String, url: String) {
        serviceScope.launch {
            try {
                val mediaItem = MediaItem.fromUri(url)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                exoPlayer.play()
                _currentSongId.value = songId
                _isPlaying.value = true
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun togglePlayPause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
    }

    fun skipToNext() {
        exoPlayer.seekToNext()
    }

    fun skipToPrevious() {
        exoPlayer.seekToPrevious()
    }

    fun seekTo(positionMs: Long) {
        exoPlayer.seekTo(positionMs)
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        val player = mediaSession?.player
        if (player != null && !player.playWhenReady) {
            stopSelf()
        }
    }
}
