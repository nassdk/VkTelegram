package com.nassdk.vkvideo.feature.videos.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoImageNetModel(
    @SerialName("url") val url: String
)