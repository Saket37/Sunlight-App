package com.example.sunlightapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.sunlightapp.R
import com.example.sunlightapp.navigation.Routes
import com.example.sunlightapp.ui.theme.CardColor


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetCard(
    index: Int,
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    navHostController: NavHostController, cardIcon: Int
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            when (index) {
                0 -> navHostController.navigate(Routes.SunScreen.path)
                1 -> navHostController.navigate(Routes.Clothing.path)
                2 -> navHostController.navigate(Routes.SkinColor.path)
                else -> {
                }
            }
        },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = CardColor.copy(0.4f),
        contentColor = Color.White
    ) {
        Box(modifier = Modifier, contentAlignment = Alignment.Center) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                val (icon, titleText, descriptionText) = createRefs()
                Image(
                    modifier = Modifier.size(24.dp).constrainAs(icon) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                    painter = painterResource(id = cardIcon),
                    contentDescription = null
                )
                Text(text = title, fontSize = 18.sp, modifier = Modifier
                    .padding(start = 8.dp)
                    .constrainAs(titleText) {
                        top.linkTo(parent.top)
                        start.linkTo(icon.end)
                    })

                Text(text = description,
                    fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .constrainAs(descriptionText) {
                            top.linkTo(titleText.bottom)
                            start.linkTo(titleText.start)
                        })
            }
        }
    }
}

