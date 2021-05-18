package com.nassdk.vkvideo.library.coreimpl.common.auth

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class AuthObserver @Inject constructor() {

    private val _events = MutableSharedFlow<Pair<Boolean, String>>()

    val events = _events.asSharedFlow() // read-only public view

    suspend fun postEvent(event: Pair<Boolean, String>) {
        _events.emit(event) // suspends until subscribers receive it
    }
}