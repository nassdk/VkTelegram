package com.nassdk.vkvideo.library.coreimpl.network.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nassdk.vkvideo.BuildConfig
import com.nassdk.vkvideo.library.coreimpl.common.data.DataStorage
import com.nassdk.vkvideo.library.coreimpl.network.connection.NetworkChecking
import com.nassdk.vkvideo.library.coreimpl.network.interceptor.CredentialsInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        @Provides
        @Singleton
        fun provideCredentialsInterceptor(dataStorage: DataStorage) = CredentialsInterceptor(
            dataStorage = dataStorage
        )

        @Provides
        @Singleton
        fun provideOkHttp3(
            loggingInterceptor: HttpLoggingInterceptor,
            credentialsInterceptor: CredentialsInterceptor
        ): OkHttpClient =
            OkHttpClient.Builder().apply {
                addInterceptor(credentialsInterceptor)
                if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
            }.build()


        @Provides
        @Singleton
        fun provideGson(): Gson = GsonBuilder()
            .setLenient()
            .create()

        @Provides
        @Singleton
        fun provideNetworkChecker(context: Context): NetworkChecking =
            NetworkChecking(context = context)

        @Provides
        @Singleton
        fun provideRetrofit(
            client: OkHttpClient,
            gson: Gson
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl(".")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }
}