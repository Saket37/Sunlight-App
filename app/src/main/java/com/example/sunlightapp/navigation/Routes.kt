package com.example.sunlightapp.navigation

sealed class Routes(val path: String) {
    object AppHome : Routes("appHome")
    object Home : Routes("home")
    object SkinColor : Routes("skinColor")
    object SunScreen : Routes("sunScreen")
    object Clothing : Routes("clothing")
}