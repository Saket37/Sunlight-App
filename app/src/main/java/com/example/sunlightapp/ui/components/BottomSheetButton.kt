package com.example.sunlightapp.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sunlightapp.ui.theme.GreenButton

@Composable
fun BottomSheetButton(modifier: Modifier = Modifier, onClick: () -> Unit, buttonText: String,backgroundColor:Color) {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = Color.White
        )
    ) {
        Text(text = buttonText)
    }
}