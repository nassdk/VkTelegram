package com.nassdk.vkvideo.feature.videos.data.repository

import com.nassdk.vkvideo.feature.videos.data.pagingsource.VideosPagingSource
import com.nassdk.vkvideo.feature.videos.domain.repository.VideosRepository
import javax.inject.Inject

class VideosRepositoryImpl @Inject constructor(
    private val pagingSource: VideosPagingSource
) : VideosRepository {
    override fun getUserVideos(): VideosPagingSource = pagingSource
}