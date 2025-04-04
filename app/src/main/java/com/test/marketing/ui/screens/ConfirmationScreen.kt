package com.test.marketing.ui.screens

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.marketing.LoginScreenViewModel

@Composable
fun ConfirmationScreen(innerPadding: PaddingValues, viewModel: LoginScreenViewModel) {
    val context = LocalActivity.current
    Column(Modifier.fillMaxSize().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {
        val phone = viewModel.loginTextFieldValue.collectAsState()
        val confirmationTextFieldValue by viewModel.confirmationTextFieldValue.collectAsState()
        Text("Enter The Confirmation Code Which Was Sent To: ${phone}", fontSize = 30.sp)
        Spacer(Modifier.size(30.dp))

        TextField(value = confirmationTextFieldValue, placeholder = {Text("Confirmation Code")}, onValueChange = {viewModel.confirmationTextFieldValue.value = it})
        Spacer(Modifier.size(60.dp))
        Button({
            viewModel.confirmationButtonCondition("The Entered Code Is Incorrect !!!",context)
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)) {
            Text("Confirm", fontSize = 20.sp, color = Color.Magenta)
        }
    }
}