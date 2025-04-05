package com.test.marketing

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.marketing.ui.User
import kotlinx.serialization.json.Json


class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalActivity.current
            val viewModel: ProfileViewModel = viewModel()
            val snackBarHostState by viewModel.snackBarHostState.collectAsState()
            val nameOfUser by viewModel.nameOfUser.collectAsState()
            val ageOfUser by viewModel.ageOfUser.collectAsState()
            val emailOfUser by viewModel.emailOfUser.collectAsState()
            Scaffold(
                snackbarHost = { SnackbarHost(snackBarHostState) }
            ) { innerPadding ->
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Please Take Your Time And Complete Your Profile:", fontSize = 20.sp)
                    Spacer(Modifier.size(20.dp))
                    TextField(
                        emailOfUser,
                        onValueChange = { viewModel.emailOfUser.value = it },
                        placeholder = { Text("Email") })
                    Spacer(Modifier.size(20.dp))
                    TextField(
                        nameOfUser,
                        onValueChange = { viewModel.nameOfUser.value = it },
                        placeholder = { Text("Full Name") })
                    Spacer(Modifier.size(20.dp))
                    TextField(
                        ageOfUser,
                        onValueChange = { viewModel.ageOfUser.value = it },
                        placeholder = { Text("Age") })
                    Spacer(Modifier.size(20.dp))
                    Button({
                        if (emailOfUser != "" && nameOfUser != "" && ageOfUser != "") {
                            var userAsString = AppPrefs.getUser()
                            val user = Json.decodeFromString<User>(userAsString!!)
                            user.email = viewModel.emailOfUser.value
                            user.name = viewModel.nameOfUser.value
                            user.age = viewModel.ageOfUser.value
                            userAsString = Json.encodeToString(user)
                            AppPrefs.setUser(userAsString)
                            context?.startActivity(
                                Intent(
                                    context,
                                    MainActivity::class.java
                                )
                            )
                            context?.finish()
                        } else {
                            viewModel.profilePageChecker("Please Fill The Inputs")
                        }
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.Green)) {
                        Text("Submit")
                    }
                }
            }
        }
    }
}