package com.nassdk.vkvideo.library.coreimpl.network.interceptor

import com.nassdk.vkvideo.library.coreimpl.common.data.DataStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
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

        val requestBuilder = originalRequest.newBuilder().url(newUrl.build())

        return chain.proceed(requestBuilder.build())
    }
}