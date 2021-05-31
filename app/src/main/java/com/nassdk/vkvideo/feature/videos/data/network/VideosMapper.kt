package com.nassdk.vkvideo.feature.videos.data.network

import android.os.Build
import com.nassdk.vkvideo.feature.videos.domain.model.VideoModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class VideosMapper @Inject constructor() {

    fun mapVideoResponse(model: VideosNetResponse) = model.response.items.map { video ->
        video.run {
            VideoModel(
                id = id,
                addingDate = convertMillisToDate(dateMillis = addingDate),
                views = views,
                comments = comments,
                title = title,
                duration = duration,
                description = description,
                player = player,
                image = images.lastOrNull()?.url.orEmpty()
            )
        }
    }

    private fun convertMillisToDate(dateMillis: Long): String {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

            val instant = Instant.ofEpochMilli(dateMillis)

            val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            formatter.format(date)
        } else ""
    }
}