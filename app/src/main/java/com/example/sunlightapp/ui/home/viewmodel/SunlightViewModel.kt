package com.example.sunlightapp.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunlightapp.R
import com.example.sunlightapp.datastore.StoreAppData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeList(
    val list: List<Sunlight> = emptyList(),
    var sunScreenType: String = "40 SPF",
    var clothingData: String = "40%",
    var skinData: String = "Light",
    val musicData: Sunlight = Sunlight("Music", "Spotify", R.drawable.ic_music)
)

data class Sunlight(
    val cardName: String, val type: String, val icon: Int
)

@HiltViewModel
class SunlightViewModel @Inject constructor(private val dataStore: StoreAppData) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeList())
    val uiState get() = _uiState

    init {
        observe()
    }

    private fun observe() {
        viewModelScope.launch(Dispatchers.Main) {
            dataStore.getAllData().map {
                _uiState.value = _uiState.value.copy(
                    sunScreenType = it.sunScreenType,
                    clothingData = it.clothingType,
                    skinData = it.colorType,
                )
            }.collect()
            _uiState.value = _uiState.value.copy(
                list = listOf(
                    Sunlight("SunScreen", _uiState.value.sunScreenType, R.drawable.ic_sun_screen),
                    Sunlight(
                        "Skin Exposure",
                        _uiState.value.clothingData,
                        R.drawable.ic_skin_exposure
                    ),
                    Sunlight("Skin Color", _uiState.value.skinData, R.drawable.ic_skin_color),
                    _uiState.value.musicData
                )
            )
        }
    }


}