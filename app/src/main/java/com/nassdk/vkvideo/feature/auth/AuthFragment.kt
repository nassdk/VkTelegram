package com.nassdk.vkvideo.feature.auth

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nassdk.vkvideo.R
import com.nassdk.vkvideo.databinding.ScreenAuthBinding
import com.nassdk.vkvideo.library.coreui.base.BaseFragment
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
            VK.login(
                activity = requireActivity(),
                scopes = setOf(
                    VKScope.VIDEO
                )
            )
        }
    }

    override fun setupViewModel() {

        viewModel.authState.onEach { authSuccess ->
            if (authSuccess)
                onAuthSuccess()
            else showError()
        }.launchWhenStarted(lifecycleScope)
    }

    private fun onAuthSuccess() = Unit

    private fun showError() {
        Toast.makeText(
            requireContext(),
            "Что-то пошло не так, пожалуйста, попробуйте позже.",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}