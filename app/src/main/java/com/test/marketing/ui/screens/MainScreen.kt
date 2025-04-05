package com.test.marketing.ui.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.marketing.AppPrefs
import com.test.marketing.MainActivity
import com.test.marketing.ui.AppsScreen
import com.test.marketing.ui.AppDetailScreen
import com.test.marketing.ui.components.BottomAppBar
import com.test.marketing.ui.components.TopAppBar
import com.test.marketing.ui.viewModel.MarketingAppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MarketingAppViewModel, activity: MainActivity) {
    val navController = rememberNavController()
    var appCurrentId = AppPrefs.getAppCurrentId()
    val isTopAppBarState by viewModel.isTopAppBarState.collectAsState()
    BackHandler {
        if (appCurrentId==-1){
            activity.finishAffinity()
        }else{
            AppPrefs.setAppCurrentId(-1)
            appCurrentId = -1
            navController.navigate(AppsScreen.AppsScreen.name)
        }
    }
//    BackHandler(
//        true
//    ) {
//        if (appCurrentId == -1) {
//            activity.finish()
//        }
//    }
    Scaffold(
        topBar = { if (isTopAppBarState)TopAppBar(viewModel,navController,) },
        bottomBar = { BottomAppBar() }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                modifier = Modifier.fillMaxSize(),
                startDestination = if (appCurrentId == -1) AppsScreen.AppsScreen.name else AppsScreen.ImageOneScreen.name
            ) {
                composable(route = AppsScreen.AppsScreen.name) {
                    AppsScreen(viewModel) { app ->
                        AppPrefs.setAppCurrentId(app.id)
                        appCurrentId = app.id
                        navController.navigate(AppsScreen.ImageOneScreen.name)
                    }
                }
                composable(route = AppsScreen.ImageOneScreen.name) {
                    AppDetailScreen(appCurrentId!!, viewModel,navController)
                }
                composable (route= AppsScreen.Profile.name){
                    ProfileScreen(viewModel,navController)
                }
            }
        }
    }
}