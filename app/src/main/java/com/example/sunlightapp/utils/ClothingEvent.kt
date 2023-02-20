package com.example.sunlightapp.utils

sealed class ClothingEvent {
    data class SelectedType(val index: Int, val selectedClothing: String) : ClothingEvent()
}
