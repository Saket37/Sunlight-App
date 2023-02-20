package com.example.sunlightapp.ui.clothing.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunlightapp.data.clothing.impl.ClothingRepository
import com.example.sunlightapp.datastore.StoreAppData
import com.example.sunlightapp.model.Clothing
import com.example.sunlightapp.utils.ClothingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ClothingState(
    val clothing: List<Clothing> = emptyList(),
    val selectedIndex: Int = 0,
    val selectedClothing: String = ""
)

@HiltViewModel
class SkinExposureViewModel @Inject constructor(
    private val clothingRepository: ClothingRepository,
    private val dataStore: StoreAppData
) :
    ViewModel() {
    private val _uiState = MutableStateFlow(ClothingState())
    val uiState get() = _uiState

    init {
        observe()
    }

    private fun observe() {
        viewModelScope.launch {
            val result = clothingRepository.getAllClothingData()
            val data = dataStore.getClothing
            _uiState.update {
                when (result) {
                    is com.example.sunlightapp.data.Result.Success -> it.copy(clothing = result.data)
                }
            }
            data.collectLatest {
                _uiState.value =
                    _uiState.value.copy(selectedIndex = it.id, selectedClothing = it.clothingType)
            }
        }
    }

    private fun onTypeSelected(index: Int, selectedClothing: String) {
        viewModelScope.launch {
            dataStore.saveClothing(id = index, clothingType = selectedClothing)
            _uiState.value =
                _uiState.value.copy(selectedIndex = index, selectedClothing = selectedClothing)
        }
    }

    fun handleEvent(clothingEvent: ClothingEvent) {
        when (clothingEvent) {
            is ClothingEvent.SelectedType -> {
                onTypeSelected(
                    index = clothingEvent.index,
                    selectedClothing = clothingEvent.selectedClothing
                )
            }
        }
    }
}