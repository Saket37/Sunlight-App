package com.example.sunlightapp.ui.clothing.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunlightapp.data.clothing.impl.ClothingRepository
import com.example.sunlightapp.model.Clothing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ClothingState(val clothing: List<Clothing> = emptyList())

@HiltViewModel
class SkinExposureViewModel @Inject constructor(private val clothingRepository: ClothingRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(ClothingState())
    val uiState get() = _uiState

    init {
        observe()
    }

    private fun observe() {
        viewModelScope.launch {
            val result = clothingRepository.getAllClothingData()
            _uiState.update {
                when (result) {
                    is com.example.sunlightapp.data.Result.Success -> it.copy(clothing = result.data)
                }
            }
        }
    }
}