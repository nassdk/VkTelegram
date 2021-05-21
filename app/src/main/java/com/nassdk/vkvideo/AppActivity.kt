package com.nassdk.vkvideo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
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

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment?
                ?: return

        val navController = host.navController
        navController.navigate(R.id.splashFragment)
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