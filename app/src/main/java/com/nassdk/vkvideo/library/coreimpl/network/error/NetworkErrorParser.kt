package com.nassdk.vkvideo.library.coreimpl.network.error

import kotlinx.serialization.json.Json
import javax.inject.Inject

class NetworkErrorParser @Inject constructor(
    private val json: Json
) {
    fun parseError(response: String?): BaseErrorNetModel.ErrorNetModel? = try {
//        val model = json.decodeFromString<BaseErrorNetModel>(string = response.orEmpty())
//        model.error
        null
    } catch (e: Exception) {
        null
    }
}