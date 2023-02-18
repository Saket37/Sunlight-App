package com.example.sunlightapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sunlightapp.ui.theme.CardColor

@Composable
fun BottomSheetCard(
    modifier: Modifier = Modifier, title: String, description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = CardColor.copy(0.4f),
        contentColor = Color.White
    ) {
        Box(modifier = Modifier.padding(vertical = 12.dp), contentAlignment = Alignment.Center) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (titleText, descriptionText) = createRefs()
                Text(text = title, modifier = Modifier.constrainAs(titleText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
                Text(text = description, modifier = Modifier.constrainAs(descriptionText) {
                    top.linkTo(titleText.bottom)
                    start.linkTo(titleText.start)
                })
            }
        }
    }
}

@Preview
@Composable
fun BottomSheetCardPreview() {
    BottomSheetCard(
        title = "Sunscreen", description = "40 SPF"
    )
}