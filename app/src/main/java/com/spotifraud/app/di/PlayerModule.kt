package com.spotifraud.app.di

import android.content.Context
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.cache.NoOpCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import androidx.media3.exoplayer.download.DownloadManager
import dagger.Module
import dagger.Provides
import dagger.InstallIn
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @Provides
    @Singleton
    fun provideDatabaseProvider(@ApplicationContext context: Context): StandaloneDatabaseProvider {
        return StandaloneDatabaseProvider(context)
    }

    @Provides
    @Singleton
    fun provideDownloadCache(@ApplicationContext context: Context, databaseProvider: StandaloneDatabaseProvider): SimpleCache {
        val downloadDirectory = File(context.getExternalFilesDir(null), "downloads")
        return SimpleCache(downloadDirectory, NoOpCacheEvictor(), databaseProvider)
    }

    @Provides
    @Singleton
    fun provideDownloadManager(
        @ApplicationContext context: Context,
        databaseProvider: StandaloneDatabaseProvider,
        cache: SimpleCache
    ): DownloadManager {
        val dataSourceFactory = DefaultDataSource.Factory(context)
        return DownloadManager(
            context,
            databaseProvider,
            cache,
            dataSourceFactory,
            { it.run() }
        )
    }
}
