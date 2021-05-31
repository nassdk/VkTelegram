package com.nassdk.vkvideo.feature.videos.data.di

import com.nassdk.vkvideo.feature.videos.data.network.VideosApi
import com.nassdk.vkvideo.feature.videos.data.repository.VideosRepositoryImpl
import com.nassdk.vkvideo.feature.videos.domain.repository.VideosRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VideosDataModule {

    @Binds
    @Singleton
    abstract fun provideVideosRepository(repositoryImpl: VideosRepositoryImpl): VideosRepository

    companion object {

        @Provides
        @Singleton
        fun provideLiveScoreApi(retrofit: Retrofit): VideosApi =
            retrofit.create(VideosApi::class.java)
    }
}