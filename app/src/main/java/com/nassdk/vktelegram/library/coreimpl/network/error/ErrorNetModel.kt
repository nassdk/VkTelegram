package com.nassdk.vktelegram.library.coreimpl.network.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorNetModel(
    @SerialName(value = "code") val code: String,
    @SerialName(value = "message") val message: String
)