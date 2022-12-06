package com.example.foodapp.screens.authorization

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.ui.theme.GrayLite1
import com.example.foodapp.ui.theme.GrayLite2
import com.example.foodapp.R
import com.example.foodapp.api.ApiService
import com.example.foodapp.data.LoginUserModel
import com.example.foodapp.data.Token
import com.example.foodapp.graphs.Graph
import com.example.foodapp.storage.SharedPreference
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(GrayLite1)
    val context = LocalContext.current
    var loginEmail by remember { mutableStateOf("") }
    var loginPassword by remember { mutableStateOf("") }
    var isActiveClickButton by remember { mutableStateOf(false) }

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
            Text(text = "E-mail", color = Color.Gray, fontFamily = NutinoRegular, fontSize = 15.sp)
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = loginEmail,
                onValueChange = { newText -> loginEmail = newText },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = {
                    Text(
                        text = "E-mail",
                        fontFamily = NutinoRegular,
                        fontSize = 17.sp,
                        modifier = Modifier.offset(x = (-16).dp)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = GrayLite1,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedIndicatorColor = Color.Gray,
                    cursorColor = Color.Gray
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Password",
                color = Color.Gray,
                fontFamily = NutinoRegular,
                fontSize = 15.sp
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = loginPassword,

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { newText -> loginPassword = newText },
                placeholder = {
                    Text(
                        text = "Password",
                        fontFamily = NutinoRegular,
                        fontSize = 17.sp,
                        modifier = Modifier.offset(x = (-16).dp)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = GrayLite1,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedIndicatorColor = Color.Gray,
                    cursorColor = Color.Gray
                ),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Forgot Password?",
                fontSize = 17.sp,
                fontFamily = NutinoRegular,
                color = Orange
            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Button(
                    enabled = isActiveClickButton,
                    onClick = {
                        val loginUser = LoginUserModel(
                            email = loginEmail,
                            password = loginPassword
                        )
                        loginUser(loginUser, navController, context)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                        .height(70.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
                    shape = RoundedCornerShape(30.dp)
                ) {

                    if (Patterns.EMAIL_ADDRESS.matcher(loginEmail)
                            .matches() && loginPassword.length > 8
                    )
                        isActiveClickButton = true

                    Text(
                        text = "Login",
                        fontFamily = NutinoRegular,
                        fontSize = 17.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

fun loginUser(loginUser: LoginUserModel, navController: NavHostController, context: Context) {

    val token = SharedPreference(context)

    ApiService.retrofit.loginUser(loginUser).enqueue(object : Callback<Token> {
        override fun onResponse(call: Call<Token>, response: Response<Token>) {
            if (response.isSuccessful) {
                navController.popBackStack()
                navController.navigate(Graph.HOME)
                token.saveToken(response.body()?.access_token.toString())
            } else {
                Toast.makeText(context, "Error code: ${response.code()}", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<Token>, t: Throwable) {
            Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
        }
    })
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}