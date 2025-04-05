package com.test.marketing

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.marketing.ui.User
import com.test.marketing.ui.screens.ConfirmationScreen
import com.test.marketing.ui.screens.LoginScreen
import kotlinx.serialization.json.Json

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPrefs.setUpAppPrefs(this)
        val userAsString = AppPrefs.getUser()
        if (userAsString!!.isNotEmpty()){
            val user = Json.decodeFromString<User>(userAsString)
            if (user.phoneNumber.isNotEmpty()) {
                if (user.age.isNotEmpty() &&user.name.isNotEmpty()
                ) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                }
            }
        }
        setContent {

            val viewModel: LoginScreenViewModel = viewModel()
            val isConfirmationScreen by viewModel.isConfirmationScreen.collectAsState()
            val snackBarState by viewModel.snackBarState.collectAsState()
            Scaffold(snackbarHost = { SnackbarHost(snackBarState) }) { innerPadding ->
                if (isConfirmationScreen) {
                    ConfirmationScreen(innerPadding, viewModel)
                } else {
                    LoginScreen(innerPadding, viewModel)
                }
            }
        }
    }
}