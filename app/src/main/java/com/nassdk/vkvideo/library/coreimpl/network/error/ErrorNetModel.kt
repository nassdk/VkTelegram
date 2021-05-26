package com.nassdk.vkvideo.library.coreimpl.network.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseErrorNetModel(
    @SerialName(value = "error") val error: ErrorNetModel
) {
    @Serializable
    data class ErrorNetModel(
        @SerialName(value = "error_code") val code: Int,
        @SerialName(value = "error_msg") val message: String
    )
}