package com.test.marketing

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    val snackBarHostState = MutableStateFlow(SnackbarHostState())
    fun profilePageChecker(error: String){
        viewModelScope.launch {
            snackBarHostState.value.showSnackbar(error)
        }
    }
    val nameOfUser = MutableStateFlow("")
    val ageOfUser = MutableStateFlow("")
    val emailOfUser = MutableStateFlow("")
}