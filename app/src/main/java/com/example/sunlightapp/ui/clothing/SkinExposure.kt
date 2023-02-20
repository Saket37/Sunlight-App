package com.example.sunlightapp.ui.clothing

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sunlightapp.R
import com.example.sunlightapp.ui.clothing.viewModel.ClothingState
import com.example.sunlightapp.ui.clothing.viewModel.SkinExposureViewModel
import com.example.sunlightapp.ui.components.TopAppBar
import com.example.sunlightapp.utils.ClothingEvent

@OptIn(ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SkinExposureScreen(navController: NavHostController = rememberNavController()) {
    val viewModel: SkinExposureViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        TopAppBar(
            title = stringResource(id = R.string.skin_exposure_app_bar),
            onNavClick = { },
            onActionClick = {})
    }, scaffoldState = scaffoldState) {
        SkinExposureContent(
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
            handleEvent = viewModel::handleEvent
        )
    }
}

@Composable
fun SkinExposureContent(uiState: ClothingState, handleEvent: (event: ClothingEvent) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start) {
            Text(text = stringResource(id = R.string.skin_exposure_selection))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 10.dp)
            ) {
                itemsIndexed(items = uiState.clothing) { index, item ->
                    SkinExposureCard(
                        onClick = {
                            handleEvent(
                                ClothingEvent.SelectedType(
                                    index = index,
                                    selectedClothing = item.percentage
                                )
                            )
                        },
                        index = index,
                        isSelected = uiState.selectedIndex == index,
                        clothingImage = item.image,
                        clothingText = item.percentage
                    )
                }
            }
        }
    }
}