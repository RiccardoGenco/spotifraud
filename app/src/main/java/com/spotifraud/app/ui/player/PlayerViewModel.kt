package com.spotifraud.app.ui.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import com.spotifraud.app.domain.repository.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val musicRepository: MusicRepository
    // controller will be provided via local provider or similar in real app
) : ViewModel() {

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    private val _currentMediaItem = MutableStateFlow<MediaItem?>(null)
    val currentMediaItem: StateFlow<MediaItem?> = _currentMediaItem

    private var player: Player? = null

    fun setPlayer(player: Player) {
        this.player = player
        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _isPlaying.value = isPlaying
            }
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                _currentMediaItem.value = mediaItem
            }
        })
    }

    fun playPause() {
        player?.let {
            if (it.isPlaying) it.pause() else it.play()
        }
    }

    fun playMedia(id: String, title: String, artist: String?, url: String) {
        viewModelScope.launch {
            val sourceUrl = musicRepository.getPlaybackSource(id, url)
            val mediaItem = MediaItem.Builder()
                .setMediaId(id)
                .setUri(sourceUrl)
                .build()
            
            player?.setMediaItem(mediaItem)
            player?.prepare()
            player?.play()
        }
    }
}
