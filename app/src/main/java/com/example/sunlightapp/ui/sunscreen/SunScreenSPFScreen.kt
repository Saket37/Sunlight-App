package com.example.sunlightapp.ui.sunscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.sunlightapp.ui.sunscreen.viewModel.SunScreenState
import com.example.sunlightapp.ui.sunscreen.viewModel.SunScreenViewModel
import com.example.sunlightapp.utils.SunScreenEvent

@OptIn(ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SunScreenSPFScreen(navController: NavHostController = rememberNavController()) {
    val viewModel: SunScreenViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        TopAppBar(
            title = stringResource(id = R.string.sun_screen_app_bar),
            onNavClick = { if (navController.previousBackStackEntry != null) navController.navigateUp() },
            onActionClick = {})
    }, scaffoldState = scaffoldState) {
        SunScreenSPFScreenContent(
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
            handleEvent = viewModel::handleEvent
        )
    }
}

@Composable
fun SunScreenSPFScreenContent(
    uiState: SunScreenState,
    handleEvent: (event: SunScreenEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sun_screen_selection),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(items = uiState.spfLevels) { index, item ->
                    SPFLevelCard(
                        spfLevelText = item.spfLevel,
                        isSelected = uiState.selectedIndex == index,
                        onClick = {
                            handleEvent(
                                SunScreenEvent.SelectedType(
                                    index = index,
                                    selectedSunScreen = item.spfLevel
                                )
                            )
                        },
                        index = index
                    )
                }
            }
            SunScreenTipCard()
        }
    }
}