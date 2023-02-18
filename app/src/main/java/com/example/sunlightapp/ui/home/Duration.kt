package com.example.sunlightapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sunlightapp.ui.components.CircularSlider
import com.example.sunlightapp.ui.theme.CardColor

@Composable
fun DurationScreen() {

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Total Duration", fontWeight = FontWeight.Bold)
        Duration()
    }
}


@Composable
fun Duration() {
    var duration by remember {
        mutableStateOf(0)
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = CardColor.copy(0.4f),
        contentColor = Color.White
    ) {
        Column(modifier = Modifier.background(color = CardColor.copy(0.4f)), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier.padding(vertical = 12.dp), contentAlignment = Alignment.Center
            ) {
                CircularSlider(modifier = Modifier.size(300.dp), stroke = 26f) {
                    duration = (it * 90).toInt()
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Duration", fontSize = 14.sp)
                    Text(text = duration.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold)

                }
            }
            RecommendationRow()
        }

    }
}

data class DurationText(
    val time: String, val color: List<Color>, val cause: String, val goals: String
)

val col1 = DurationText(
    time = "1hr 30min", color = listOf(Color.White), cause = "Vitamin D", goals = "Recommended"
)
val col2 = DurationText(
    time = "50min", color = listOf(Color.White), cause = "Vitamin D", goals = "Daily Goal"
)
val col3 = DurationText(
    time = "30min", color = listOf(Color.White), cause = "Sunburn", goals = "Time Remaining"
)
val durationRow = listOf(col1, col2, col3)

@Composable
fun Recommendation(time: String, cause: String, goals: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = time, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            Modifier
                .padding(horizontal = 8.dp)
                .height(3.dp)
                .fillMaxWidth(), color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = cause, fontSize = 14.sp)
        Text(text = goals, fontSize = 14.sp)
    }
}

@Composable
fun RecommendationRow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, bottom = 16.dp, end = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(durationRow) {
                Recommendation(time = it.time, cause = it.cause, goals = it.goals)
            }
        }
    }
}

@Preview
@Composable
fun DurationPreview() {
    Duration()
}

@Preview
@Composable
fun RecommendationPreview() {
    RecommendationRow()
}