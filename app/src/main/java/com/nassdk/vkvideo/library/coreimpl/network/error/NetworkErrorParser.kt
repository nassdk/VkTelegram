package com.nassdk.vkvideo.library.coreimpl.network.error

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NetworkErrorParser @Inject constructor(
    private val json: Json
) {
    fun parseError(response: String?): BaseErrorNetModel.ErrorNetModel? = try {
        val data = json.decodeFromString<BaseErrorNetModel>(response!!)
        data.error
    } catch (e: Exception) {
        null
    }
}