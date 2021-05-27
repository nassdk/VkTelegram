package com.nassdk.vkvideo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nassdk.vkvideo.feature.splash.SplashFragment
import com.nassdk.vkvideo.library.coreimpl.common.auth.AuthObserver
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    @Inject
    lateinit var authObserver: AuthObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_screen)

        if (savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.container, SplashFragment())
            fragmentTransaction.commit()
        }
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
}