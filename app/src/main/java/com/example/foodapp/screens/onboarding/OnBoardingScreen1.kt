package com.example.foodapp.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.foodapp.R
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange

@Composable
fun OnBoardingScreen1() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Orange),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.on_boaring_1),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = "Fastest Delivery \n 24/7",
            modifier = Modifier.align(CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontFamily = NutinoRegular,
        )
    }
}