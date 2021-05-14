package com.nassdk.vktelegram.library.coreapi.common.error

import com.test.roomsample.library.coreimpl.common.error.ErrorWrapper

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorWrapper
}