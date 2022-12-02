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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.R
import com.example.foodapp.api.ApiService
import com.example.foodapp.data.RegisterUserModel
import com.example.foodapp.data.Token
import com.example.foodapp.graphs.Graph
import com.example.foodapp.navigation.OnBoardingScreen
import com.example.foodapp.storage.SharedPreference
import com.example.foodapp.ui.theme.GrayLite1
import com.example.foodapp.ui.theme.GrayLite2
import com.example.foodapp.ui.theme.Orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RegisterScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(GrayLite1)
    val context = LocalContext.current
    var registerEmail by remember { mutableStateOf("") }
    var registerPassword by remember { mutableStateOf("") }
    var registerFullName by remember { mutableStateOf("") }
    var registerPhoneNumber by remember { mutableStateOf("") }
    var isActiveClickButton by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayLite1)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .background(
                    color = GrayLite2,
                    shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.cooking_register),
                contentDescription = null,
                modifier = Modifier.size(130.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)
                .padding(top = 15.dp)
        ) {
            Text(text = "E-mail", color = Color.Gray)
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = registerEmail,
                onValueChange = { newText -> registerEmail = newText },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = { Text(text = "E-mail", modifier = Modifier.offset(x = (-16).dp)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = GrayLite1,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedIndicatorColor = Color.Gray,
                    cursorColor = Color.Gray
                ),
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Password", color = Color.Gray)
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = registerPassword,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { newText -> registerPassword = newText },
                placeholder = { Text(text = "Password", modifier = Modifier.offset(x = (-16).dp)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = GrayLite1,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedIndicatorColor = Color.Gray,
                    cursorColor = Color.Gray
                ),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Full name", color = Color.Gray)
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = registerFullName,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { newText -> registerFullName = newText },
                placeholder = { Text(text = "Full name", modifier = Modifier.offset(x = (-16).dp)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = GrayLite1,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedIndicatorColor = Color.Gray,
                    cursorColor = Color.Gray
                ),
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Phone number", color = Color.Gray)
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = registerPhoneNumber,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { newText -> registerPhoneNumber = newText },
                placeholder = { Text(text = "Phone number", modifier = Modifier.offset(x = (-16).dp)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = GrayLite1,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedIndicatorColor = Color.Gray,
                    cursorColor = Color.Gray
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        enabled = isActiveClickButton,
                        onClick = {
                            val listRegisterUser = RegisterUserModel(
                                name = registerFullName,
                                email = registerEmail,
                                password = registerPassword
                            )
                            createUser(listRegisterUser, navController, context)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
                        shape = RoundedCornerShape(30.dp)
                    ) {

                        if (
                            Patterns.PHONE.matcher(registerPhoneNumber).matches() &&
                            Patterns.EMAIL_ADDRESS.matcher(registerEmail).matches() &&
                            registerFullName.isNotEmpty() && registerPassword.length > 8
                        ) {
                            isActiveClickButton = true
                        }

                        Text(text = "Register Now", color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(
                        onClick = {
                            navController.navigate(OnBoardingScreen.LoginScreen.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp)
                            .height(70.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
                        shape = RoundedCornerShape(30.dp)
                    ) {

                        Text(text = "Cancel", color = Color.White)
                    }
                }
            }
        }
    }
}

fun createUser(
   registerUser: RegisterUserModel,
    navController: NavHostController,
    context: Context
) {

    val token = SharedPreference(context)

    ApiService.retrofit.createUser(registerUser).enqueue(object: Callback<Token>{
        override fun onResponse(call: Call<Token>, response: Response<Token>) {
            if(response.isSuccessful){
                navController.popBackStack()
                navController.navigate(Graph.HOME)
                token.saveToken(response.body()?.access_token.toString())
                Toast.makeText(context, "Token: ${response.body()?.access_token}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error code: ${response.code()}", Toast.LENGTH_SHORT).show()
            }
        }
        override fun onFailure(call: Call<Token>, t: Throwable) {
            Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
        }
    })
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}