package com.nassdk.vkvideo.feature.auth

import androidx.lifecycle.viewModelScope
import com.nassdk.vkvideo.library.coreimpl.common.auth.AuthObserver
import com.nassdk.vkvideo.library.coreimpl.common.data.DataStorage
import com.nassdk.vkvideo.library.coreui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authObserver: AuthObserver,
    private val dataStorage: DataStorage
) : BaseViewModel() {

    private val _authState: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val authState get() = _authState.asSharedFlow().filterNotNull()

    init {
        handleAuthStatusChanges()
    }

    private fun handleAuthStatusChanges() {

        authObserver.events
            .onEach {
                if (it.first)
                    dataStorage.storeAccessToken(token = it.second)
                _authState.value = it.first
            }.launchIn(viewModelScope)
    }
}