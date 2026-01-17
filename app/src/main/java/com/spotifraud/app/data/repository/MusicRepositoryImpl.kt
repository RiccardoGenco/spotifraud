package com.spotifraud.app.data.repository

import com.spotifraud.app.data.local.dao.DownloadDao
import com.spotifraud.app.domain.model.ExtractionResult
import com.spotifraud.app.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val downloadDao: DownloadDao
) : MusicRepository {

    override fun getDownloads(): Flow<List<ExtractionResult>> {
        return downloadDao.getAllDownloads().map { entities ->
            entities.map { entity ->
                ExtractionResult(
                    id = entity.id,
                    title = entity.title,
                    artist = entity.artist,
                    durationMs = entity.durationMs,
                    streamUrl = if (entity.isDownloaded) entity.localPath else null,
                    thumbnailUrl = entity.thumbnailUrl,
                    source = "Local"
                )
            }
        }
    }

    override suspend fun getPlaybackSource(id: String, remoteUrl: String): String {
        val localDownload = downloadDao.getDownloadById(id)
        return if (localDownload?.isDownloaded == true && localDownload.localPath != null) {
            val file = File(localDownload.localPath)
            if (file.exists()) {
                file.absolutePath
            } else {
                remoteUrl
            }
        } else {
            remoteUrl
        }
    }
}
