package com.spotifraud.app.domain.repository

import com.spotifraud.app.domain.model.ExtractionResult
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getDownloads(): Flow<List<ExtractionResult>>
    suspend fun getPlaybackSource(id: String, remoteUrl: String): String
}
