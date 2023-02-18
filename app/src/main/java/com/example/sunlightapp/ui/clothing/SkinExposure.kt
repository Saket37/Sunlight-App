package com.example.sunlightapp.ui.clothing

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.sunlightapp.R
import com.example.sunlightapp.ui.clothing.viewModel.ClothingState
import com.example.sunlightapp.ui.clothing.viewModel.SkinExposureViewModel
import com.example.sunlightapp.ui.components.TopAppBar

@OptIn(ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SkinExposureScreen() {
    val viewModel: SkinExposureViewModel = hiltViewModel()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        TopAppBar(
            title = stringResource(id = R.string.skin_exposure_app_bar),
            onNavClick = { },
            onActionClick = {})
    }, scaffoldState = scaffoldState) {
        SkinExposureContent(uiState = viewModel.uiState.collectAsStateWithLifecycle().value)
    }
}

@Composable
fun SkinExposureContent(uiState: ClothingState) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start) {
            Text(text = stringResource(id = R.string.skin_exposure_selection))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 10.dp)
            ) {
                items(uiState.clothing) {
                    SkinExposureCard(
                        onClick = { },
                        isSelected = false,
                        clothingImage = it.image,
                        clothingText = it.percentage
                    )
                }
            }
        }
    }
}