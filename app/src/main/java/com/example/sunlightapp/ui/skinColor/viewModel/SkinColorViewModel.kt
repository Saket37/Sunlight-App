package com.example.sunlightapp.ui.skinColor.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunlightapp.data.skinColor.impl.SkinColorRepository
import com.example.sunlightapp.datastore.StoreAppData
import com.example.sunlightapp.model.SkinColor
import com.example.sunlightapp.utils.SkinColorEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SkinColorState(
    val skinColor: List<SkinColor> = emptyList(),
    val selectedIndex: Int = 0,
    val selectedColor: String = ""
)

@HiltViewModel
class SkinColorViewModel @Inject constructor(
    private val skinColorRepository: SkinColorRepository,
    private val dataStore: StoreAppData
) :
    ViewModel() {
    private val _uiState = MutableStateFlow(SkinColorState())
    val uiState get() = _uiState

    init {
        observe()
    }

    private fun observe() {
        viewModelScope.launch {
            val result = skinColorRepository.getAllSkinColorData()
            val data = dataStore.getSkinColor
            _uiState.update {
                when (result) {
                    is com.example.sunlightapp.data.Result.Success -> it.copy(skinColor = result.data)
                }
            }
            data.collectLatest {
                _uiState.value =
                    _uiState.value.copy(selectedIndex = it.id, selectedColor = it.colorType)
            }
        }
    }

    private fun onTypeSelected(index: Int, selectedColor: String) {
        viewModelScope.launch {
            dataStore.saveSkinColor(id = index, colorType = selectedColor)
            _uiState.value =
                _uiState.value.copy(selectedIndex = index, selectedColor = selectedColor)
        }
    }

    fun handleEvent(skinColorEvent: SkinColorEvent) {
        when (skinColorEvent) {
            is SkinColorEvent.SelectedType -> {
                onTypeSelected(
                    index = skinColorEvent.index,
                    selectedColor = skinColorEvent.selectedColor
                )
            }
        }
    }

}