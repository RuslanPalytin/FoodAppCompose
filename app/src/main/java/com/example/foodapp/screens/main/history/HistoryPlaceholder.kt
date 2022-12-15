package com.example.foodapp.screens.main.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodapp.R
import com.example.foodapp.ui.theme.NutinoRegular

@Composable
fun HistoryPlaceHolder() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.histiry_placeholder),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "No history yet", fontFamily = NutinoRegular, fontSize = 28.sp)
        }
    }
}

@Preview (showBackground = true)
@Composable
fun HistoryPlaceHolderPreview() {
    HistoryPlaceHolder()
}