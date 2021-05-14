package com.nassdk.vktelegram.library.coreimpl.network.interceptor

//class CredentialsInterceptor : Interceptor {
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//
//        val originalRequest = chain.request()
//        val originalUrl = originalRequest.url
//
//        val newUrl = originalUrl.newBuilder()
//            .addQueryParameter("key", PRIVATE_API_KEY)
//            .addQueryParameter("secret", PRIVATE_API_SECRET)
//
//        val requestBuilder = originalRequest.newBuilder().url(newUrl.build())
//
//        if (BuildConfig.DEBUG) {
//            Timber.tag("REQUEST_URL_PRINT").e(newUrl.toString())
//        }
//
//        return chain.proceed(requestBuilder.build())
//    }
//}