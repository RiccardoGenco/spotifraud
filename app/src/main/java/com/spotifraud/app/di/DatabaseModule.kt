package com.spotifraud.app.di

import android.content.Context
import androidx.room.Room
import com.spotifraud.app.data.local.AppDatabase
import com.spotifraud.app.data.local.dao.DownloadDao
import dagger.Module
import dagger.Provides
import dagger.InstallIn
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "spotifraud_db"
        ).build()
    }

    @Provides
    fun provideDownloadDao(database: AppDatabase): DownloadDao {
        return database.downloadDao()
    }
}
