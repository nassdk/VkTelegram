package com.nassdk.vkvideo.feature.splash

import androidx.lifecycle.lifecycleScope
import com.nassdk.vkvideo.R
import com.nassdk.vkvideo.feature.auth.AuthFragment
import com.nassdk.vkvideo.feature.videos.presentation.VideosFragment
import com.nassdk.vkvideo.library.coreimpl.common.data.DataStorage
import com.nassdk.vkvideo.library.coreui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.screen_splash) {

    @Inject lateinit var dataStorage: DataStorage

    override fun prepareUi() {

        lifecycleScope.launchWhenStarted {
            delay(3000)

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.container,
                    if (dataStorage.getAccessToken().isNullOrBlank()) AuthFragment() else VideosFragment()
                ).commit()
        }
    }
}