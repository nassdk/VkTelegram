package com.nassdk.vktelegram.feature.auth

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.nassdk.vktelegram.feature.auth.AuthStore.*
import com.nassdk.vktelegram.library.coreimpl.common.auth.AuthObserver
import com.nassdk.vktelegram.library.coreimpl.common.data.DataStorage
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class AuthStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val authObserver: AuthObserver,
    private val dataStorage: DataStorage
) {

    fun create(): AuthStore =
        object : AuthStore, Store<Intent, State, Label> by storeFactory.create(
            name = "AuthStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl()
        ) {}

    private class BootstrapperImpl : SuspendBootstrapper<Action>() {

        override suspend fun bootstrap() {
            dispatch(action = Action.HandleAuthStatus)
        }
    }

    private inner class ExecutorImpl : SuspendExecutor<Intent, Action, State, Result, Label>() {

        override suspend fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.HandleAuthStatus -> handleAuthStatusChanges()
            }
        }

        private suspend fun handleAuthStatusChanges() {

            authObserver.events
                .collect {
                    if (it.first) {
                        dataStorage.storeAccessToken(token = it.second)
                        publish(label = Label.OpenVideos)
                    } else publish(label = Label.AuthError)
                }
        }
    }

    private class ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result) = State()
    }


    sealed class Action {
        object HandleAuthStatus : Action()
    }

    class Result
}