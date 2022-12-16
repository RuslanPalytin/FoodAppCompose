package com.example.foodapp.screens.main.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodapp.R
import com.example.foodapp.ui.theme.NutinoRegular

@Composable
fun ItemAdditionally() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(4.dp)) {
        Box(modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(20.dp))) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.tomato_2),
                contentDescription = null,
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Veggie tomato mix", fontSize = 12.sp, fontFamily = NutinoRegular)
            Spacer(modifier = Modifier.width(3.dp))
            Card(shape = RoundedCornerShape(5.dp), backgroundColor = Color.Gray) {
                Text(
                    text = "200",
                    fontSize = 10.sp,
                    fontFamily = NutinoRegular,
                    modifier = Modifier.padding(horizontal = 3.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemAdditionallyPreview() {
    ItemAdditionally()
}