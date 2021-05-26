package com.nassdk.vkvideo.library.coreimpl.network.error

import javax.inject.Inject

class NetworkErrorParser @Inject constructor(

) {
    fun parseError(response: String?): BaseErrorNetModel.ErrorNetModel? = try {
//        val model = gson.fromJson(response, BaseErrorNetModel::class.java)
//        model.error
        null
    } catch (e: Exception) {
        null
    }
}