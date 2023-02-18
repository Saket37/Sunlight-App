package com.example.sunlightapp.utils

sealed class SkinColorEvent {
    data class SelectedType(val index: Int, val selectedColor: String) : SkinColorEvent()
}