package com.nassdk.vkvideo.library.coreimpl.common.di

import android.content.Context
import com.nassdk.vkvideo.library.coreapi.common.error.ErrorHandler
import com.nassdk.vkvideo.library.coreapi.common.resourcemanager.ResourceManager
import com.nassdk.vkvideo.library.coreimpl.common.auth.AuthObserver
import com.nassdk.vkvideo.library.coreimpl.common.data.DataStorage
import com.nassdk.vkvideo.library.coreimpl.common.error.ErrorHandlerImpl
import com.nassdk.vkvideo.library.coreimpl.common.resourcemanager.ResourceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModule {

    @Binds
    @Singleton
    abstract fun bindErrorHandler(impl: ErrorHandlerImpl): ErrorHandler

    @Binds
    @Singleton
    abstract fun bindResourceManager(impl: ResourceManagerImpl): ResourceManager

    companion object {

        @Provides
        @Singleton
        fun provideDataStore(@ApplicationContext context: Context) = DataStorage(context = context)

        @Provides
        @Singleton
        fun provideAuthObserver() = AuthObserver()
    }
}