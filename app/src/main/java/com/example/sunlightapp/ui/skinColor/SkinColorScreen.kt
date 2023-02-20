package com.example.sunlightapp.ui.skinColor

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sunlightapp.R
import com.example.sunlightapp.ui.components.TopAppBar
import com.example.sunlightapp.ui.skinColor.viewModel.SkinColorState
import com.example.sunlightapp.ui.skinColor.viewModel.SkinColorViewModel
import com.example.sunlightapp.utils.SkinColorEvent

@OptIn(ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SkinColorScreen(navController: NavHostController = rememberNavController()) {
    val viewModel: SkinColorViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        TopAppBar(
            title = stringResource(id = R.string.skin_color_app_bar),
            onNavClick = { },
            onActionClick = {})
    }, scaffoldState = scaffoldState) {
        SkinColorScreenContent(
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
            handleEvent = viewModel::handleEvent
        )
    }
}

@Composable
fun SkinColorScreenContent(uiState: SkinColorState, handleEvent: (event: SkinColorEvent) -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start) {
            Text(
                text = stringResource(id = R.string.skin_color_selection),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(items = uiState.skinColor) { index, item ->
                    SkinColorCard(
                        skinColor = item.color,
                        skinColorText = item.colorText,
                        isSelected = uiState.selectedIndex == index,
                        index = index,
                        onClick = {
                            handleEvent(
                                SkinColorEvent.SelectedType(
                                    index = index,
                                    selectedColor = item.colorText
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}