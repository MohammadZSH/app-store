package com.test.marketing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.marketing.ui.screens.ConfirmationScreen
import com.test.marketing.ui.screens.LoginScreen

class LoginActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: LoginScreenViewModel = viewModel ()
            val isConfirmationScreen by viewModel.isConfirmationScreen.collectAsState()
            val snackBarState by viewModel.snackBarState.collectAsState()
            Scaffold (snackbarHost = { SnackbarHost(snackBarState) }){ innerPadding->
                if (isConfirmationScreen){
                    ConfirmationScreen(innerPadding,viewModel)
                }else{
                    LoginScreen(innerPadding,viewModel)
                }
            }
        }
    }
}