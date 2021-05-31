package com.nassdk.vkvideo.feature.videos.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosInnerNetModel(
    @SerialName("count") val count: Int,
    @SerialName("items") val items: List<VideoNetModel>
)