package com.example.sunlightapp.utils

sealed class SunScreenEvent {
    data class SelectedType(val index: Int, val selectedSunScreen: String) : SunScreenEvent()
}
