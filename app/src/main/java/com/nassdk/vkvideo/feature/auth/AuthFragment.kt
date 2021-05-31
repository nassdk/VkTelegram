package com.nassdk.vkvideo.feature.auth

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nassdk.vkvideo.R
import com.nassdk.vkvideo.databinding.ScreenAuthBinding
import com.nassdk.vkvideo.library.coreui.base.BaseFragment
import com.nassdk.vkvideo.library.coreui.util.FloatingSnackBar
import com.nassdk.vkvideo.library.coreui.util.launchWhenStarted
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AuthFragment : BaseFragment(R.layout.screen_auth) {

    private var _viewBinding: ScreenAuthBinding? = null

    private val viewBinding get() = _viewBinding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun prepareUi() {

        _viewBinding = ScreenAuthBinding.bind(requireView())

        viewBinding.makeAuth.setOnClickListener {
            makeAuth()
        }
    }

    override fun setupViewModel() {

        viewModel.authState.onEach { authSuccess ->
            when (authSuccess) {
                true -> onAuthSuccess()
                false -> onAuthError()
                else -> Unit
            }
        }.launchWhenStarted(lifecycleScope)
    }

    private fun makeAuth() {
        VK.login(
            activity = requireActivity(),
            scopes = setOf(
                VKScope.VIDEO
            )
        )
    }

    private fun onAuthSuccess() = Unit
    private fun onAuthError() = FloatingSnackBar.make(
        activity = requireActivity(),
        text = getString(R.string.screen_auth_error_message),
        hideAfterShow = true,
        actionButtonListener = { makeAuth() }
    ).show()

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}