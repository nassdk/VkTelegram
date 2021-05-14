package com.nassdk.vktelegram.library.coreimpl.network.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nassdk.vktelegram.library.coreimpl.network.connection.NetworkChecking
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationContext::class)
abstract class NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        @Provides
        @Singleton
        fun provideOkHttp3(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        @Provides
        @Singleton
        fun provideJson(): Json = Json(
            JsonConfiguration.Stable.copy(
                isLenient = true,
                prettyPrint = true,
                ignoreUnknownKeys = true
            )
        )

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
                .baseUrl(".")
                .client(client)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
    }
}