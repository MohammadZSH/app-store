package com.test.marketing.ui

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.test.marketing.AppPrefs
import com.test.marketing.ui.viewModel.MarketingAppViewModel

@Composable
fun AppDetailScreen(
    appId: Int,
    viewModel: MarketingAppViewModel,
    navController: NavHostController,
) {
    var appCurrentId = AppPrefs.getAppCurrentId()
    var app = viewModel.appList.find { it.id == appId }
    BackHandler {
        AppPrefs.setAppCurrentId(-1)
        appCurrentId = -1
        navController.navigate(AppsScreen.AppsScreen.name)
        viewModel.isTopAppBarState.value = true
    }
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            AsyncImage(
                model = app?.imageUrl, contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}