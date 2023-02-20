package com.example.sunlightapp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class StoreAppData(private val context: Context) {
    companion object {
        private val Context.userPreferenceDataStore: DataStore<Preferences> by preferencesDataStore(
            "sunlight"
        )
        private val SKIN_COLOR_TYPE_INDEX = intPreferencesKey("skin_color_id")
        private val SKIN_COLOR_TYPE = stringPreferencesKey("skin_color")
        private val SUN_SCREEN_TYPE_INDEX = intPreferencesKey("sun_screen_id")
        private val SUN_SCREEN_TYPE = stringPreferencesKey("sun_screen")
        private val CLOTHING_TYPE_INDEX = intPreferencesKey("clothing_index")
        private val CLOTHING_TYPE = stringPreferencesKey("clothing")
    }

    val getSkinColor: Flow<SkinColorType> =
        context.userPreferenceDataStore.data.map { preferences ->
            SkinColorType(
                id = preferences[SKIN_COLOR_TYPE_INDEX] ?: 1,
                colorType = preferences[SKIN_COLOR_TYPE] ?: "Light"
            )
        }
    val getSunScreen: Flow<SunScreenType> =
        context.userPreferenceDataStore.data.map { preferences ->
            SunScreenType(
                id = preferences[SUN_SCREEN_TYPE_INDEX] ?: 1,
                sunScreenType = preferences[SUN_SCREEN_TYPE] ?: "SPF 20"
            )
        }
    val getClothing: Flow<ClothingType> = context.userPreferenceDataStore.data.map { preferences ->
        ClothingType(
            id = preferences[CLOTHING_TYPE_INDEX] ?: 0,
            clothingType = preferences[CLOTHING_TYPE] ?: "40%"
        )
    }

    fun getAllData(): Flow<HomeScreenData> = flow {
        runBlocking {
            context.userPreferenceDataStore.data.map { preferences ->
                HomeScreenData(
                    clothingType = preferences[CLOTHING_TYPE] ?: "40%",
                    colorType = preferences[SKIN_COLOR_TYPE] ?: "Light",
                    sunScreenType = preferences[SUN_SCREEN_TYPE] ?: "SPF 20"
                )
            }
        }.first()
    }

    suspend fun saveSkinColor(id: Int, colorType: String) {
        context.userPreferenceDataStore.edit { preferences ->
            preferences[SKIN_COLOR_TYPE_INDEX] = id
            preferences[SKIN_COLOR_TYPE] = colorType
        }
    }

    suspend fun saveSunScreen(id: Int, sunScreenType: String) {
        context.userPreferenceDataStore.edit { preferences ->
            preferences[SUN_SCREEN_TYPE_INDEX] = id
            preferences[SUN_SCREEN_TYPE] = sunScreenType
        }
    }

    suspend fun saveClothing(id: Int, clothingType: String) {
        context.userPreferenceDataStore.edit { preferences ->
            preferences[CLOTHING_TYPE_INDEX] = id
            preferences[CLOTHING_TYPE] = clothingType
        }
    }
}