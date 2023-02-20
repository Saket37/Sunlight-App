package com.example.sunlightapp.datastore

data class SkinColorType(val id: Int, val colorType: String)
data class SunScreenType(val id: Int, val sunScreenType: String)
data class ClothingType(val id: Int, val clothingType: String)
data class HomeScreenData(
    val colorType: String,
    val sunScreenType: String,
    val clothingType: String
)
