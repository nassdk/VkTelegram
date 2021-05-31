package com.nassdk.vkvideo.feature.videos.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface VideosApi {

    @GET("video.get")
    suspend fun getVideos(
        @Query("count") count: Int,
        @Query("offset") offset: Int
    ): VideosNetResponse
}