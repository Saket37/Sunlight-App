package com.example.sunlightapp.ui.sunscreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunlightapp.data.sunscreen.impl.SunScreenRepository
import com.example.sunlightapp.model.SunScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SunScreenState(val spfLevels: List<SunScreen> = emptyList())

@HiltViewModel
class SunScreenViewModel @Inject constructor(private val sunScreenRepository: SunScreenRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(SunScreenState())
    val uiState get() = _uiState

    init {
        observe()
    }

    private fun observe() {
        viewModelScope.launch {
            val result = sunScreenRepository.getAllSunScreenData()
            _uiState.update {
                when (result) {
                    is com.example.sunlightapp.data.Result.Success -> it.copy(spfLevels = result.data)
                }
            }
        }
    }
}