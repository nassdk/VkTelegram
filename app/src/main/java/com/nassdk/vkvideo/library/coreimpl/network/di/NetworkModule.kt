package com.nassdk.vkvideo.library.coreimpl.network.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nassdk.vkvideo.library.coreimpl.common.data.DataStorage
import com.nassdk.vkvideo.library.coreimpl.network.connection.NetworkChecking
import com.nassdk.vkvideo.library.coreimpl.network.interceptor.CredentialsInterceptor
import com.vk.api.sdk.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
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
        fun provideJson(): Json {
            return Json(Json.Default) {
                isLenient = true
                ignoreUnknownKeys = true
            }
        }

        @Provides
        @Singleton
        fun provideNetworkChecker(context: Context): NetworkChecking =
            NetworkChecking(context = context)

        @Provides
        @Singleton
        fun provideRetrofit(
            client: OkHttpClient,
            json: Json
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://api.vk.com/method/")
                .client(client)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
    }
}