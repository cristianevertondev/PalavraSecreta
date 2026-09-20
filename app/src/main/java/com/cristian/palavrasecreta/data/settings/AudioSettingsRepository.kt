package com.cristian.palavrasecreta.data.settings

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Preferências de áudio do jogador. Usa um DataStore separado do progresso,
 * para não interferir nos dados existentes.
 */
data class AudioSettings(
    val musicOn: Boolean = true,
    val sfxOn: Boolean = true
)

private val Context.audioDataStore by preferencesDataStore(name = "audio_settings")

class AudioSettingsRepository(private val context: Context) {

    private object Keys {
        val musicOn = booleanPreferencesKey("music_on")
        val sfxOn = booleanPreferencesKey("sfx_on")
    }

    val settings: Flow<AudioSettings> =
        context.audioDataStore.data.map { prefs ->
            AudioSettings(
                musicOn = prefs[Keys.musicOn] ?: true,
                sfxOn = prefs[Keys.sfxOn] ?: true
            )
        }

    suspend fun setMusicOn(on: Boolean) {
        context.audioDataStore.edit { prefs -> prefs[Keys.musicOn] = on }
    }

    suspend fun setSfxOn(on: Boolean) {
        context.audioDataStore.edit { prefs -> prefs[Keys.sfxOn] = on }
    }
}