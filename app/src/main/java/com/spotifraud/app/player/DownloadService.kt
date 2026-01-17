package com.spotifraud.app.player

import android.app.Notification
import androidx.media3.exoplayer.download.Download
import androidx.media3.exoplayer.download.DownloadManager
import androidx.media3.exoplayer.download.DownloadService
import androidx.media3.exoplayer.scheduler.Scheduler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DownloadService : DownloadService(
    1, // FOREGROUND_NOTIFICATION_ID
    DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL,
    "spotifraud_download_channel",
    com.spotifraud.app.R.string.app_name,
    0
) {

    @Inject
    lateinit var downloadManager: DownloadManager

    override fun getDownloadManager(): DownloadManager = downloadManager

    override fun getScheduler(): Scheduler? = null // For simplicity, no scheduling for now

    override fun getForegroundNotification(
        downloads: MutableList<Download>,
        notMetRequirements: Int
    ): Notification {
        // We will need a proper notification builder here
        // For now, using a placeholder
        return Notification.Builder(this, "spotifraud_download_channel")
            .setContentTitle("Downloading...")
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .build()
    }
}
