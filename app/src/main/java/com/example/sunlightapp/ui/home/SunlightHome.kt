package com.example.sunlightapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.sunlightapp.ui.components.BottomSheetButton
import com.example.sunlightapp.ui.components.BottomSheetCard
import com.example.sunlightapp.ui.theme.TransparentColor
import com.example.sunlightapp.ui.theme.GreenButton

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SunlightHome() {
    val navController = rememberNavController()
    val stateChange: Boolean = false
    val bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
        confirmStateChange = { stateChange })
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        sheetContent = { SheetContent(isExpanded = stateChange) },
        topBar = {
            com.example.sunlightapp.ui.components.TopAppBar(
                title = "Bengaluru, Karnataka",
                onNavClick = { }, onActionClick = {})
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 200.dp,
        sheetElevation = 8.dp,
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

@Composable
fun SheetContent(isExpanded: Boolean) {
    var itemCount: Int
    Box(contentAlignment = Alignment.TopStart) {
        Column {
            Text(text = "PRACTICE")
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (!isExpanded) {
                    items(2) {
                        BottomSheetCard(title = "Sunscreen", description = "40 SPF")
                    }
                    items(2) {
                        BottomSheetButton(
                            onClick = { },
                            buttonText = "SCHEDULE",
                            backgroundColor = GreenButton
                        )

                    }
                } else {
                    items(4) {
                        BottomSheetCard(title = "Sunscreen", description = "40 SPF")
                    }
                    items(2) {
                        BottomSheetButton(
                            onClick = { },
                            buttonText = "SCHEDULE",
                            backgroundColor = GreenButton
                        )

                    }
                }


            }
        }
    }
}