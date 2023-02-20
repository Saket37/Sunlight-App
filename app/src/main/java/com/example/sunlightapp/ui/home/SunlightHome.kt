package com.example.sunlightapp.ui.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sunlightapp.datastore.StoreAppData
import com.example.sunlightapp.ui.components.BottomSheetButton
import com.example.sunlightapp.ui.components.BottomSheetCard
import com.example.sunlightapp.ui.components.BottomSheetDivider
import com.example.sunlightapp.ui.components.WeatherCard
import com.example.sunlightapp.ui.home.viewmodel.HomeList
import com.example.sunlightapp.ui.home.viewmodel.SunlightViewModel
import com.example.sunlightapp.ui.theme.BlueButton
import com.example.sunlightapp.ui.theme.GreenButton
import com.example.sunlightapp.ui.theme.TransparentColor

enum class BottomSheetScroll {
    COLLAPSED, IS_EXPANDING, EXPANDED
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun SunlightHome(navController: NavHostController = rememberNavController()) {
    val bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
    )
    val viewModel: SunlightViewModel = hiltViewModel()
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreAppData(context)
    BottomSheetScaffold(
        sheetContent = {
            SheetContent(
                isExpanded = bottomSheetState.isExpanded,
                navController = navController,
                uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
                bottomSheetState.direction,
                bottomSheetState.progress.fraction, bottomSheetState.progress.to,
                bottomSheetState.progress.from
            )
        },
        sheetElevation = 16.dp,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        backgroundColor = TransparentColor.copy(0.38f),
        topBar = {
            com.example.sunlightapp.ui.components.TopAppBar(
                title = "Bengaluru, Karnataka",
                onNavClick = { }, onActionClick = {})
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 180.dp,
        sheetBackgroundColor = TransparentColor.copy(.38f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            DurationScreen()
        }
    }
}


data class CardButton(val text: String, val color: Color)

val cardButton =
    listOf<CardButton>(CardButton("SCHEDULE", GreenButton), CardButton("START", BlueButton))

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetContent(
    isExpanded: Boolean,
    navController: NavHostController = rememberNavController(),
    uiState: HomeList,
    direction: Float,
    fraction: Float,
    to: BottomSheetValue,
    from: BottomSheetValue
) {
    var isExpanding by remember {
        mutableStateOf(false)
    }
    when {
        from == BottomSheetValue.Collapsed && to == BottomSheetValue.Expanded -> isExpanding =
            true
        from == BottomSheetValue.Collapsed && to == BottomSheetValue.Collapsed -> isExpanding =
            false
        else -> {}
    }
    Log.d("HOME_LIST", uiState.list.toString())
    Box(
        modifier = Modifier
            .fillMaxWidth(), contentAlignment = Alignment.TopStart
    ) {
        Column(modifier = Modifier.padding(top = 8.dp)) {
            BottomSheetDivider()
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "PRACTICE")
            }
            /*Text(text = "direction $direction")
            Text(text = "fraction $fraction")
            Text(text = "to $to")
            Text(text = "from $from")*/

            if (uiState.list.isNotEmpty()) {
                Log.d("SLICE", uiState.list.subList(0, 3).toString())
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (!isExpanding) {
                    if (uiState.list.isNotEmpty()) {
                        itemsIndexed(items = uiState.list.subList(0, 2)) { index, item ->
                            BottomSheetCard(
                                index = index,
                                title = item.cardName,
                                description = item.type,
                                navHostController = navController, cardIcon = item.icon
                            )
                        }
                    }
                } else {
                    itemsIndexed(items = uiState.list) { index, item ->
                        BottomSheetCard(
                            index = index,
                            title = item.cardName,
                            description = item.type,
                            navHostController = navController, cardIcon = item.icon
                        )
                    }
                }
            }
            if (isExpanding) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                ) { WeatherCard() }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cardButton) {
                    BottomSheetButton(
                        onClick = { },
                        buttonText = it.text,
                        backgroundColor = it.color
                    )
                }
            }
        }
    }
}
