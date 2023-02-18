package com.example.sunlightapp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class SkinColorType(val id: Int, val colorType: String)
class StoreAppData(private val context: Context) {
    companion object {
        private val Context.userPreferenceDataStore: DataStore<Preferences> by preferencesDataStore(
            "sunlight"
        )
        private val SKIN_COLOR_TYPE_INDEX = intPreferencesKey("skin_color_id")
        private val SKIN_COLOR_TYPE = stringPreferencesKey("skin_color")
        //private val SUN_SCREEN_TYPE = stringPreferencesKey("sun_screen")
        //private val CLOTHING = stringPreferencesKey("clothing")
    }

    val getSkinColor: Flow<SkinColorType> =
        context.userPreferenceDataStore.data.map { preferences ->
            SkinColorType(
                id = preferences[SKIN_COLOR_TYPE_INDEX] ?: 1,
                colorType = preferences[SKIN_COLOR_TYPE] ?: "Light"
            )
        }

    suspend fun saveSkinColor(id: Int, colorType: String) {
        context.userPreferenceDataStore.edit { preferences ->
            preferences[SKIN_COLOR_TYPE_INDEX] = id
            preferences[SKIN_COLOR_TYPE] = colorType
        }
    }
}