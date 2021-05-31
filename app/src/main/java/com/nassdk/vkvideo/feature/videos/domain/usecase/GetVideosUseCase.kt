package com.nassdk.vkvideo.feature.videos.domain.usecase

import androidx.paging.PagingSource
import com.nassdk.vkvideo.feature.videos.domain.model.VideoModel
import com.nassdk.vkvideo.feature.videos.domain.repository.VideosRepository
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val repository: VideosRepository
) {
    operator fun invoke(): PagingSource<Int, VideoModel> = repository.getUserVideos()
}