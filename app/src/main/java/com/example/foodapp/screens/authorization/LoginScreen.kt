package com.example.foodapp.screens.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.ui.theme.GrayLite1
import com.example.foodapp.ui.theme.GrayLite2
import com.example.foodapp.R
import com.example.foodapp.graphs.Graph
import com.example.foodapp.ui.theme.Orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(GrayLite1)
    var loginEmail by remember { mutableStateOf("") }
    var loginPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayLite1)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(
                    color = GrayLite2,
                    shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.cooking_login),
                contentDescription = null,
                modifier = Modifier.size(170.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)
                .padding(top = 70.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = loginEmail,
                onValueChange = { newText -> loginEmail = newText },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text(text = "E-mail") },
                placeholder = { Text(text = "E-mail") },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = GrayLite1)
            )
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = loginPassword,

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { newText -> loginPassword = newText },
                label = { Text(text = "Password", ) },
                placeholder = { Text(text = "Password") },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = GrayLite1),
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Forgot Password?", color = Orange)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Button(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Graph.HOME)
                    },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp).height(70.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text(text = "Login", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}