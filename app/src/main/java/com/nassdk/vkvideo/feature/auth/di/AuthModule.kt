package com.nassdk.vkvideo.feature.auth.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.nassdk.vkvideo.feature.auth.AuthStoreFactory
import com.nassdk.vkvideo.library.coreimpl.common.auth.AuthObserver
import com.nassdk.vkvideo.library.coreimpl.common.data.DataStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AuthModule {

    @Provides
    fun provideAuthStoreFactory(
        dataStorage: DataStorage,
        authObserver: AuthObserver
    ) = AuthStoreFactory(
        storeFactory = DefaultStoreFactory,
        dataStorage = dataStorage,
        authObserver = authObserver
    ).create()
}