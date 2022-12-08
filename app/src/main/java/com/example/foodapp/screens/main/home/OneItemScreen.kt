package com.example.foodapp.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R

@Composable
fun OneItemScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),

    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_selected_cancel),
            contentDescription = null,
            modifier = Modifier.padding(top = 25.dp, start = 10.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(250.dp)
                .align(CenterHorizontally),
            backgroundColor = Color.White,
            elevation = 100.dp
        ) {
            Image(painter = painterResource(id = R.drawable.ic_tomato), contentDescription = null)
        }
    }
}

@Preview
@Composable
fun OneItemScreenPreview() {
    OneItemScreen()
}