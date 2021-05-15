package com.nassdk.vktelegram.feature.splash

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nassdk.vktelegram.R
import com.nassdk.vktelegram.library.coreui.base.BaseFragment
import kotlinx.coroutines.delay

class SplashFragment : BaseFragment(R.layout.screen_splash) {

    override fun prepareUi() {

        lifecycleScope.launchWhenStarted {
            delay(3000)
            findNavController().navigate(R.id.authFragment)
        }
    }
}