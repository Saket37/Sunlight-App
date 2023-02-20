package com.example.sunlightapp.ui.sunscreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunlightapp.data.sunscreen.impl.SunScreenRepository
import com.example.sunlightapp.datastore.StoreAppData
import com.example.sunlightapp.model.SunScreen
import com.example.sunlightapp.utils.SunScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SunScreenState(
    val spfLevels: List<SunScreen> = emptyList(),
    val selectedIndex: Int = 0,
    val selectedSunScreen: String = ""
)

@HiltViewModel
class SunScreenViewModel @Inject constructor(
    private val sunScreenRepository: SunScreenRepository,
    private val dataStore: StoreAppData
) :
    ViewModel() {
    private val _uiState = MutableStateFlow(SunScreenState())
    val uiState get() = _uiState

    init {
        observe()
    }

    private fun observe() {
        viewModelScope.launch {
            val result = sunScreenRepository.getAllSunScreenData()
            val data = dataStore.getSunScreen
            _uiState.update {
                when (result) {
                    is com.example.sunlightapp.data.Result.Success -> it.copy(spfLevels = result.data)
                }
            }
            data.collectLatest {
                _uiState.value =
                    _uiState.value.copy(selectedIndex = it.id, selectedSunScreen = it.sunScreenType)
            }
        }
    }

    private fun onTypeSelected(index: Int, selectedSunScreen: String) {
        viewModelScope.launch {
            dataStore.saveSunScreen(id = index, sunScreenType = selectedSunScreen)
            _uiState.value =
                _uiState.value.copy(selectedIndex = index, selectedSunScreen = selectedSunScreen)
        }
    }

    fun handleEvent(sunScreenEvent: SunScreenEvent) {
        when (sunScreenEvent) {
            is SunScreenEvent.SelectedType -> {
                onTypeSelected(
                    index = sunScreenEvent.index,
                    selectedSunScreen = sunScreenEvent.selectedSunScreen
                )
            }
        }
    }
}