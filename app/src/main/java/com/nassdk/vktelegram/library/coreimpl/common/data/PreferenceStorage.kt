package com.nassdk.vktelegram.library.coreimpl.common.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException

object PreferenceKeys {
    val accessToken = stringPreferencesKey("PREF_ACCESS_TOKEN")
}

class DataStorage(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREF_NAME)

    suspend fun storeAccessToken(token: String?) {

        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.accessToken] = token ?: ""
        }
    }

    suspend fun getAccessToken() = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }
        .map { preferences ->
            preferences[PreferenceKeys.accessToken]
        }.firstOrNull()

    companion object {
        const val PREF_NAME = "DATA_STORE_COMMON_FILE"
    }
}