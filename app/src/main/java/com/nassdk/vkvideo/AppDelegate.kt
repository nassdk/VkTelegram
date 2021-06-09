package com.nassdk.vkvideo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import timber.log.Timber

@HiltAndroidApp
class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()

        initRealm()
        initTimber()
    }

    private fun initRealm() {
        Realm.init(this)
    }

    private fun initTimber() {

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}