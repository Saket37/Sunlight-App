package com.example.sunlightapp.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sunlightapp.R

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier, title: String, onNavClick: () -> Unit, onActionClick: () -> Unit
) {
    TopAppBar(modifier = modifier,
        title = { Text(text = title, fontSize = 20.sp) },
        navigationIcon = {
            IconButton(onClick = { onNavClick() }) {
                Icon(
                    modifier = Modifier
                        .width(17.dp)
                        .height(20.dp),
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = { onActionClick() }) {
                Icon(
                    modifier = Modifier
                        .width(17.dp)
                        .height(20.dp),
                    painter = painterResource(id = R.drawable.ic_help),
                    contentDescription = null
                )
            }
        })
}

@Preview
@Composable
fun TopAppBarPreview() {
    com.example.sunlightapp.ui.components.TopAppBar(title = "Bengaluru, Karnataka",
        onNavClick = {},
        onActionClick = {})
}