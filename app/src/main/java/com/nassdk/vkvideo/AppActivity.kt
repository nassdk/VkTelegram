package com.nassdk.vkvideo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nassdk.vkvideo.feature.auth.AuthFragment
import com.nassdk.vkvideo.feature.splash.SplashFragment
import com.nassdk.vkvideo.library.coreimpl.common.auth.AuthObserver
import com.nassdk.vkvideo.library.coreimpl.common.data.DataStorage
import com.nassdk.vkvideo.library.coreui.util.FloatingSnackBar
import com.nassdk.vkvideo.library.coreui.util.makeForward
import com.nassdk.vkvideo.library.coreui.util.makeReplace
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    @Inject
    lateinit var authObserver: AuthObserver
    @Inject
    lateinit var dataStorage: DataStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_screen)

        if (savedInstanceState == null) {
            makeForward(fragment = SplashFragment())
        }

        VK.addTokenExpiredHandler(tokenTracker)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        VK.onActivityResult(
            requestCode = requestCode,
            resultCode = resultCode,
            data = data,
            callback = object : VKAuthCallback {

                override fun onLogin(token: VKAccessToken) {
                    lifecycleScope.launchWhenStarted {
                        authObserver.postEvent(
                            event = Pair(
                                first = true,
                                second = token.accessToken
                            )
                        )
                    }
                }

                override fun onLoginFailed(errorCode: Int) {
                    lifecycleScope.launchWhenStarted {
                        authObserver.postEvent(event = Pair(first = false, second = ""))
                    }
                }
            }
        )
    }

    private val tokenTracker = object : VKTokenExpiredHandler {

        override fun onTokenExpired() {

            lifecycleScope.launchWhenStarted {

                dataStorage.clearAccessToken()

                makeReplace(fragment = AuthFragment())

                FloatingSnackBar.make(
                    activity = this@AppActivity,
                    text = getString(R.string.app_screen_token_expired_message),
                    hideAfterShow = true
                ).show()
            }
        }
    }
}