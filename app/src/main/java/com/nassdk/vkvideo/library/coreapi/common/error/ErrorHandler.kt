package com.nassdk.vkvideo.library.coreapi.common.error

import com.nassdk.vkvideo.library.coreimpl.common.error.ErrorWrapper

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorWrapper
}