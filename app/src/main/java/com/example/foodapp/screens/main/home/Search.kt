package com.example.foodapp.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodapp.R
import com.example.foodapp.ui.theme.GrayLite1
import com.example.foodapp.ui.theme.Roboto

@Composable
fun Search(search: MutableState<String>) {

    var isVisible by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(color = if (isVisible) GrayLite1 else Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.75f),
            value = search.value,
            onValueChange = { newText -> search.value = newText },
            placeholder = {
                Text(
                    text = if (isVisible) "Выберите адрес доставки" else "Search",
                    color = Color.Gray,
                    fontFamily = Roboto,
                    fontSize = 18.sp
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (isVisible) GrayLite1 else Color.White,
                focusedIndicatorColor = if (isVisible) GrayLite1 else Color.White,
                unfocusedIndicatorColor = if (isVisible) GrayLite1 else Color.White
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_navigate),
                contentDescription = null,
                modifier = Modifier
                    .alpha(if(isVisible) 1f else 0f)
                    .align(Alignment.CenterVertically)
                    .size(24.dp),
                tint = Color.Gray,
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_cancel),
                contentDescription = null,
                modifier = Modifier
                    .alpha(if(!isVisible) 1f else 0f)
                    .align(Alignment.CenterVertically)
                    .size(34.dp),
                tint = Color.Gray
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(24.dp)
                    .clickable {
                        isVisible = !isVisible
                    },
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(25.dp))
        }
    }
}