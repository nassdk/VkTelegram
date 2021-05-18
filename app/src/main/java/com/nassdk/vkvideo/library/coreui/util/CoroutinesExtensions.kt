package com.nassdk.vkvideo.library.coreui.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull as mapNotNullFlow

internal inline fun <T, R : Any> Flow<T>.mapNotNull(crossinline mapper: (T) -> R?): Flow<R> =
    mapNotNullFlow { mapper(it) }