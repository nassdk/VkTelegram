package com.nassdk.vkvideo.feature.videos.domain.repository

import androidx.paging.PagingSource
import com.nassdk.vkvideo.feature.videos.domain.model.VideoModel

interface VideosRepository {
    fun getUserVideos(): PagingSource<Int, VideoModel>
}