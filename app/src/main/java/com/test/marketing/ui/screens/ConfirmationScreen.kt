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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.marketing.LoginScreenViewModel
import com.test.marketing.ui.theme.MarketingAppColors

@Composable
fun ConfirmationScreen(innerPadding: PaddingValues, viewModel: LoginScreenViewModel) {
    val context = LocalActivity.current
    val timeCounter by viewModel.timerCounter.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val phone = viewModel.loginTextFieldValue.collectAsState()
        val confirmationTextFieldValue by viewModel.confirmationTextFieldValue.collectAsState()
        Text(buildAnnotatedString {
            append("Enter The Confirmation Code Which Was Sent To: ")
            withStyle(style = SpanStyle(color = Color.Red)) {
                append(phone.value)
            }
        }, fontSize = 30.sp)
        Spacer(Modifier.size(30.dp))

        TextField(
            value = confirmationTextFieldValue,
            placeholder = { Text("Confirmation Code") },
            onValueChange = {
                if (confirmationTextFieldValue.length < 4) viewModel.confirmationTextFieldValue.value =
                    it
            })
        Spacer(Modifier.size(60.dp))
        Button({
            viewModel.confirmationButtonCondition("The Entered Code Is Incorrect !!!", context)
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)) {
            Text("Confirm", fontSize = 20.sp, color = Color.Magenta)
        }
        Spacer(Modifier.size(20.dp))
        Text("Time Left To Try Again: 0$timeCounter")
        Spacer(Modifier.size(20.dp))
        Button({
            viewModel.timerCounter.value=3
            viewModel.isConfirmationScreen.value = false
        }, colors = ButtonDefaults.buttonColors(containerColor = MarketingAppColors.LogOutButtonColor), enabled = timeCounter==0) {
            Text("Edit Number")
        }
    }
}