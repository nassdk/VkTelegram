package com.nassdk.vktelegram.library.coreimpl.common.di

import com.nassdk.vktelegram.library.coreapi.common.error.ErrorHandler
import com.nassdk.vktelegram.library.coreapi.common.resourcemanager.ResourceManager
import com.nassdk.vktelegram.library.coreimpl.common.error.ErrorHandlerImpl
import com.nassdk.vktelegram.library.coreimpl.common.resourcemanager.ResourceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationContext::class)
abstract class CommonModule {

    @Binds
    @Singleton
    abstract fun bindErrorHandler(impl: ErrorHandlerImpl): ErrorHandler

    @Binds
    @Singleton
    abstract fun bindResourceManager(impl: ResourceManagerImpl): ResourceManager
}