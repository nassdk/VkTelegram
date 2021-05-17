package com.nassdk.vktelegram.feature.auth

import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.nassdk.vktelegram.R
import com.nassdk.vktelegram.databinding.ScreenAuthBinding
import com.nassdk.vktelegram.library.coreui.base.BaseFragment
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : BaseFragment(R.layout.screen_auth) {

    private val viewBinding: ScreenAuthBinding by viewBinding()

    @Inject
    lateinit var store: AuthStore

    override fun prepareUi() {

        bind(this@AuthFragment.lifecycle.asMviLifecycle(), BinderLifecycleMode.START_STOP) {
            store.labels.bindTo { label ->
                when (label) {
                    AuthStore.Label.AuthError -> showError()
                    AuthStore.Label.OpenVideos -> onAuthSuccess()
                }
            }
        }

        viewBinding.makeAuth.setOnClickListener {
            VK.login(
                activity = requireActivity(),
                scopes = setOf(
                    VKScope.VIDEO
                )
            )
        }
    }

    private fun onAuthSuccess() {
        Toast.makeText(requireContext(), "Успешная авторизация", Toast.LENGTH_SHORT).show()
//        findNavController().navigate() TODO
    }

    private fun showError() {
        Toast.makeText(
            requireContext(),
            "Что-то пошло не так, пожалуйста, попробуйте позже.",
            Toast.LENGTH_SHORT
        ).show()
    }
}