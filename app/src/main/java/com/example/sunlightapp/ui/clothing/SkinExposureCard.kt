package com.example.sunlightapp.ui.clothing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sunlightapp.R
import com.example.sunlightapp.ui.theme.CardColor
import com.example.sunlightapp.ui.theme.TransparentColor

// TODO Change background color of selected icon

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SkinExposureCard(
    onClick: (Int) -> Unit,
    index: Int,
    isSelected: Boolean,
    clothingImage: Int,
    clothingText: String
) {
    val cardColor = if (isSelected) {
        CardColor.copy(0.4f)
    } else {
        TransparentColor.copy(0.38f)
    }
    Card(
        backgroundColor = cardColor,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke(index) },
        shape = RoundedCornerShape(16.dp),
        contentColor = Color.White
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 2.dp)
        ) {
            val (content, selected) = createRefs()
            CardContent(
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }, clothingImage = clothingImage, clothingText = clothingText
            )
            if (isSelected) {
                Image(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .constrainAs(selected) {
                            top.linkTo(content.top)
                            end.linkTo(parent.end)
                        },
                    painter = painterResource(id = R.drawable.ic_selected),
                    contentDescription = null,
                )
            }

        }
    }
}

@Composable
fun CardContent(modifier: Modifier = Modifier, clothingImage: Int, clothingText: String) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = clothingImage),
            contentDescription = null,
        )
        Text(text = clothingText, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun SkinExposureCardPreview() {
    SkinExposureCard(
        onClick = { },
        isSelected = false,
        clothingImage = R.drawable.ic_clothing_w20,
        clothingText = "40%", index = 0
    )
}