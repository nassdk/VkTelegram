package com.nassdk.vktelegram.library.coreimpl.network.error

import javax.inject.Inject

class NetworkErrorParser @Inject constructor() {

    fun parseError(response: String?): ErrorNetModel? = try {
        null //FIXME
//        gson.fromJson(response, ErrorNetModel::class.java)
    } catch (e: Exception) {
        null
    }
}