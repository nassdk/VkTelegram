package com.nassdk.vkvideo.feature.videos.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nassdk.vkvideo.feature.videos.data.network.VideosApi
import com.nassdk.vkvideo.feature.videos.data.network.VideosMapper
import com.nassdk.vkvideo.feature.videos.domain.model.VideoModel
import javax.inject.Inject

class VideosPagingSource @Inject constructor(
    private val api: VideosApi,
    private val mapper: VideosMapper
) : PagingSource<Int, VideoModel>() {

    override fun getRefreshKey(state: PagingState<Int, VideoModel>): Int? {

        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoModel> {

        val offset = params.key ?: 0

        return try {
            val response = api.getVideos(count = PAGE_SIZE, offset = offset)
            val videos = mapper.mapVideoResponse(model = response)

            val nextOffset = if ((response.response.count - offset) < PAGE_SIZE) null else offset + PAGE_SIZE
            val prevOffset = if (offset > PAGE_SIZE) offset - PAGE_SIZE else null

            LoadResult.Page(data = videos, prevKey = prevOffset, nextKey = nextOffset)
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }

    private companion object {
        private const val PAGE_SIZE = 10
    }
}