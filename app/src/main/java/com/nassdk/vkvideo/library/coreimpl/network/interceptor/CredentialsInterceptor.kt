package com.nassdk.vkvideo.library.coreimpl.network.interceptor

import com.nassdk.vkvideo.BuildConfig
import com.nassdk.vkvideo.library.coreimpl.common.data.DataStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class CredentialsInterceptor @Inject constructor(
    private val dataStorage: DataStorage
) : Interceptor {

    private val accessToken by lazy {
        runBlocking(Dispatchers.IO) {
            dataStorage.getAccessToken()
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("access_token", accessToken)
            .addQueryParameter("v", API_VERSION)

        val requestBuilder = originalRequest.newBuilder().url(newUrl.build())

        if (BuildConfig.DEBUG) {
            Timber.tag("VK_VIDEO_REQUEST_LOG").e(newUrl.toString())
        }

        return chain.proceed(requestBuilder.build())
    }

    private companion object {
        private const val API_VERSION = "5.131"
    }
}