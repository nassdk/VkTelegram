package com.nassdk.vkvideo.feature.videos.domain.model

data class VideoModel(
    val id: Long,
    val title: String,
    val description: String,
    val addingDate: String,
    val duration: Int,
    val views: Int,
    val comments: String,
    val image: String,
    val player: String
)