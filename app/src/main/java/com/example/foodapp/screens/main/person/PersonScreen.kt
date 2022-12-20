package com.example.foodapp.screens.main.person

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodapp.graphs.Graph
import com.example.foodapp.navigation.BottomBarScreen
import com.example.foodapp.navigation.OnBoardingScreen
import com.example.foodapp.screens.main.BottomBar
import com.example.foodapp.screens.onboarding.OnBoardingScreen1
import com.example.foodapp.storage.SharedPreference
import com.example.foodapp.ui.theme.Orange

@Composable
fun PersonScreen(navController: NavHostController) {

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            SharedPreference(context).saveToken("")
            Toast.makeText(context, "Token deleted", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "LogOut")
        }
    }
}