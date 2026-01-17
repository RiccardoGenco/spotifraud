package com.spotifraud.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloads")
data class DownloadEntity(
    @PrimaryKey val id: String,
    val title: String,
    val artist: String?,
    val localPath: String?,
    val thumbnailUrl: String?,
    val isDownloaded: Boolean,
    val durationMs: Long
)
