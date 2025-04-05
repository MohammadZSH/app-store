package com.test.marketing

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.marketing.ui.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

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
            val user = User(
                phoneNumber = loginTextFieldValue.value
            )
            val userAsString= Json.encodeToString(user)
            AppPrefs.setUser(userAsString)
            if (user.email.isNotEmpty()&&user.name.isNotEmpty()&&user.age!=""){
                context?.startActivity(Intent(context, MainActivity::class.java))
                context?.finish()
            }else{
                context?.startActivity(Intent(context, ProfileActivity::class.java))
                context?.finish()
            }
        }else{
            viewModelScope.launch {
                snackBarState.value.showSnackbar(error)
            }
        }
    }
}