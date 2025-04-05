package com.test.marketing.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.test.marketing.AppPrefs
import com.test.marketing.ui.AppsScreen
import com.test.marketing.ui.viewModel.MarketingAppViewModel

@Composable
fun ProfileScreen(
    viewModel: MarketingAppViewModel,
    navController: NavHostController
) {
    var appCurrentId = AppPrefs.getAppCurrentId()
    BackHandler {
        viewModel.isTopAppBarState.value = true
        AppPrefs.setAppCurrentId(-1)
        appCurrentId = -1
        navController.navigate(AppsScreen.AppsScreen.name)
    }
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.fillMaxWidth()){
            IconButton({

            }){
//                Icon()
            }
        }
    }
}