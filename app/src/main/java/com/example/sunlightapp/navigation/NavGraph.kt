package com.example.sunlightapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.sunlightapp.ui.clothing.SkinExposureScreen
import com.example.sunlightapp.ui.home.SunlightHome
import com.example.sunlightapp.ui.skinColor.SkinColorScreen
import com.example.sunlightapp.ui.sunscreen.SunScreenSPFScreen

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.AppHome.path
) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(startDestination = Routes.Home.path, route = Routes.AppHome.path) {
            composable(route = Routes.Home.path) {
                SunlightHome(navController)
            }
            composable(route = Routes.Clothing.path) {
                SkinExposureScreen(navController)
            }
            composable(route = Routes.SkinColor.path) {
                SkinColorScreen(navController)
            }
            composable(route = Routes.SunScreen.path) {
                SunScreenSPFScreen(navController)
            }
        }
    }
}