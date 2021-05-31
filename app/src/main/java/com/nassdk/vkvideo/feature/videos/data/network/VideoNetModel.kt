package com.nassdk.vkvideo.feature.videos.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoNetModel(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("adding_date") val addingDate: Long,
    @SerialName("views") val views: Int,
    @SerialName("duration") val duration: Int,
    @SerialName("comments") val comments: String,
    @SerialName("image") val images: List<VideoImageNetModel>,
    @SerialName("player") val player: String
)