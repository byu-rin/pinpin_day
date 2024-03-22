package com.byurin.trip.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "PreferenceManager"

enum class OptionMenu { EDIT, DELETE, SHARE }

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = TAG
)

data class OptionPreferences(val optionMenu: OptionMenu, val hideCompleted: Boolean)

@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val preferenceFlow = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val optionMenu = OptionMenu.valueOf(
                (preferences[PreferencesKeys.SORT_ORDER] ?: OptionMenu.DELETE.name).toString()
            )

        }

    suspend fun updateOptionMenu(optionMenu: OptionMenu) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SORT_ORDER] = optionMenu.name
        }
    }

    private object PreferencesKeys {
        val SORT_ORDER = stringPreferencesKey("sort_order")
        val HIDE_COMPLETED = booleanPreferencesKey("hide_completed")
    }
}
