package com.cristian.palavrasecreta.data.progress

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "game_progress")

/**
 * Persistência local do progresso via DataStore (Preferences).
 * Guarda apenas o conjunto de ids dos níveis concluídos — tudo o que a
 * progressão e os desbloqueios precisam. Funciona offline e sobrevive ao
 * fechamento do app.
 */
class ProgressRepository(private val context: Context) {

    private object Keys {
        val completedLevels = stringSetPreferencesKey("completed_levels")
    }

    /** Fluxo observável dos ids dos níveis concluídos (como String). */
    val completedLevelIds: Flow<Set<Int>> =
        context.dataStore.data.map { prefs ->
            prefs[Keys.completedLevels].orEmpty().mapNotNull { it.toIntOrNull() }.toSet()
        }

    /** Marca um nível como concluído de forma idempotente. */
    suspend fun markLevelCompleted(levelId: Int) {
        context.dataStore.edit { prefs ->
            val current = prefs[Keys.completedLevels].orEmpty().toMutableSet()
            current.add(levelId.toString())
            prefs[Keys.completedLevels] = current
        }
    }
}