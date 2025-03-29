package com.test.marketing.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.marketing.ui.AppsScreen
import com.test.marketing.ui.AppDetailScreen
import com.test.marketing.ui.components.BottomAppBar
import com.test.marketing.ui.components.TopAppBar
import com.test.marketing.ui.viewModel.MarketingAppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MarketingAppViewModel) {
    val navController = rememberNavController()
    val activity = LocalActivity.current
    val settings = activity?.getSharedPreferences("settings",Context.MODE_PRIVATE)
    var appCurrentId = settings?.getInt("APP_ID", -1)
    BackHandler {
        if (appCurrentId==-1){
            activity?.finishAffinity()
        }else{
            Log.i("taggg","back:appCurrentID : $appCurrentId")
            settings?.edit()?.putInt("APP_ID",-1)?.commit()
            appCurrentId = -1
            navController.navigate(AppsScreen.AppsScreen.name)
        }
    }
    Scaffold(
        topBar = { TopAppBar(viewModel) },
        bottomBar = { BottomAppBar() }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(top = 100.dp, bottom = 100.dp)) {
            NavHost(
                navController = navController,
                modifier = Modifier.fillMaxSize(),
                startDestination = if (appCurrentId == -1) AppsScreen.AppsScreen.name else AppsScreen.ImageOneScreen.name
            ) {
                composable(route = AppsScreen.AppsScreen.name) {
                    AppsScreen(viewModel) { _app ->
                        settings?.edit()?.putInt("APP_ID",_app.id)?.commit()
                        appCurrentId = _app.id
                        Log.i("taggg","click: appCurrentID : $appCurrentId")
                        navController.navigate(AppsScreen.ImageOneScreen.name)
                    }
                }
                composable(route = AppsScreen.ImageOneScreen.name) {
                    AppDetailScreen(appCurrentId!!, viewModel)
                }
            }
        }
    }
}