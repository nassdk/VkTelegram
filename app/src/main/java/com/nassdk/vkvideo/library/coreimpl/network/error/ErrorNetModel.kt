package com.nassdk.vkvideo.library.coreimpl.network.error

import kotlinx.serialization.SerialName

//@Serializable
data class BaseErrorNetModel(
    @SerialName(value = "error") val error: ErrorNetModel
) {
    data class ErrorNetModel(
        @SerialName(value = "error_code") val code: Int,
        @SerialName(value = "error_msg") val message: String
    )
}