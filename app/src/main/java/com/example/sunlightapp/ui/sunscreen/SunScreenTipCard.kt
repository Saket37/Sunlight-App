package com.example.sunlightapp.ui.sunscreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sunlightapp.R
import com.example.sunlightapp.ui.theme.CardColor

@Composable
fun SunScreenTipCard() {
    Card(
        backgroundColor = CardColor.copy(.4f),
        modifier = Modifier.fillMaxWidth(),
        contentColor = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Wearing sunscreen is one of the best — and easiest — ways to protect your skin's appearance and health at any age.Sunscreen may help prevent the sun's rays from causing photoaging and skin cancer.",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview
@Composable
fun SunScreenTipCardPreview() {
    SunScreenTipCard()
}