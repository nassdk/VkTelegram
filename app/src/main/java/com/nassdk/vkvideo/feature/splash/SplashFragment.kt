package com.nassdk.vkvideo.feature.splash

import androidx.lifecycle.lifecycleScope
import com.nassdk.vkvideo.R
import com.nassdk.vkvideo.library.coreui.base.BaseFragment
import kotlinx.coroutines.delay

class SplashFragment : BaseFragment(R.layout.screen_splash) {

    override fun prepareUi() {

        lifecycleScope.launchWhenStarted {
            delay(3000)
        }
    }
}