package com.example.sunlightapp.ui.sunscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sunlightapp.R
import com.example.sunlightapp.ui.theme.CardColor
import com.example.sunlightapp.ui.theme.TransparentColor

@Composable
fun SPFLevelCard(
    spfLevelText: String, isSelected: Boolean, onClick: (Int) -> Unit, index: Int
) {
    val cardColor = if (isSelected) CardColor.copy(.4f) else TransparentColor.copy(.38f)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke(index) },
        backgroundColor = cardColor,
        contentColor = Color.White, shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = spfLevelText, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            if (isSelected) {
                Image(
                    painter = painterResource(id = R.drawable.ic_selected),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun SPFLevelCardPreview() {
    SPFLevelCard(spfLevelText = "SPF 15", isSelected = true, onClick = {}, index = 0)
}