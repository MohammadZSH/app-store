package com.test.marketing.ui.screens

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
fun LoginScreen(innerPadding: PaddingValues, viewModel: LoginScreenViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter Your Phone Number", fontSize = 30.sp)
        Spacer(Modifier.size(30.dp))
        val valueOfPhoneNumTxtField by viewModel.loginTextFieldValue.collectAsState()


        TextField(
            value = valueOfPhoneNumTxtField,
            placeholder = { Text("Phone Number") },
            onValueChange = {
                viewModel.loginTextFieldValue.value = it
            })
        Spacer(Modifier.size(60.dp))
        Button({
            viewModel.loginButtonCondition("Not A Valid Number")
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)) {
            Text("Send Confirmation Code!", fontSize = 20.sp, color = Color.Magenta)
        }
    }
}