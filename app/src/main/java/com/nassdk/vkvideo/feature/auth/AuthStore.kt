package com.nassdk.vkvideo.feature.auth

import com.arkivanov.mvikotlin.core.store.Store
import com.nassdk.vkvideo.feature.auth.AuthStore.*

interface AuthStore : Store<Intent, State, Label> {

    class Intent
    class State

    sealed class Label {
        object OpenVideos : Label()
        object AuthError : Label()
    }
}