package com.test.marketing

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    var isConfirmationScreen = MutableStateFlow(false)
    var loginTextFieldValue = MutableStateFlow("")
    var confirmationTextFieldValue = MutableStateFlow("")
    var snackBarState = MutableStateFlow(SnackbarHostState())
    fun loginButtonCondition(error: String) {
        if (loginTextFieldValue.value != "0" &&
            loginTextFieldValue.value.length == 11 &&
            loginTextFieldValue.value.startsWith("09")
        ) {
            isConfirmationScreen.value = true
        }else{
            viewModelScope.launch {
                snackBarState.value.showSnackbar(error)
            }
        }
    }

    fun confirmationButtonCondition(error: String, context: Activity?){
        if(confirmationTextFieldValue.value==loginTextFieldValue.value.drop(7)){
            context?.startActivity(Intent(context, MainActivity::class.java))
        }else{
            viewModelScope.launch {
                snackBarState.value.showSnackbar(error)
            }
        }
    }
}