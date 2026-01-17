package com.spotifraud.app.di

import com.spotifraud.app.data.repository.ExtractionRepositoryImpl
import com.spotifraud.app.domain.repository.ExtractionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExtractionRepository(
        impl: ExtractionRepositoryImpl
    ): ExtractionRepository

    @Binds
    @Singleton
    abstract fun bindMusicRepository(
        impl: MusicRepositoryImpl
    ): MusicRepository
}
