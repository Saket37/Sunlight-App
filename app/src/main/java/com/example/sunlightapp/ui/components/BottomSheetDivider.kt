package com.example.sunlightapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetDivider() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.Center
    ) {
        Divider(
            color = Color.White,
            modifier = Modifier
                .size(width = 80.dp, height = 8.dp)
                .clip(shape = RoundedCornerShape(4.dp))
        )
    }
}