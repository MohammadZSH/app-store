package com.test.marketing.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
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
import com.test.marketing.ui.AppsScreen
import com.test.marketing.ui.AppDetailScreen
import com.test.marketing.ui.components.BottomAppBar
import com.test.marketing.ui.components.TopAppBar
import com.test.marketing.ui.viewModel.MarketingAppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MarketingAppViewModel, activity: Activity, appCurrentId: Int) {
    val navController = rememberNavController()
    var appCurrentId = appCurrentId
    val isTopAppBarState by viewModel.isTopAppBarState.collectAsState()
//    val launcher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) { isGranted: Boolean ->
//        if (isGranted) {
//            // Permission Accepted: Do something
//            Log.i("ASKFORPERMISSION", "GOT PERMISSION")
//            sendTestNotification(activity,"aa","aaa")
//        } else {
//            // Permission Denied: Do something
//            Log.i("ASKFORPERMISSION", "DID NOT GET PERMISSION")
//            Toast.makeText(activity, "DID NOT GET PERMISSION", Toast.LENGTH_SHORT).show()
//        }
//    }
//    val ac = LocalActivity.current!!.finishAffinity()
    BackHandler {
        appCurrentId =-1
        activity.finishAffinity()
    }
    Scaffold(
        topBar = { if (isTopAppBarState) TopAppBar(viewModel, navController) },
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
                    AppDetailScreen(appCurrentId!!, viewModel, navController, activity)
                }
                composable(route = AppsScreen.Profile.name) {
                    ProfileScreen(viewModel, navController, activity)
                }
            }
        }
    }


//    if (isTopAppBarState&&appCurrentId==-1) {
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.Bottom,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(20.dp)
//        ) {
//
//
//
//            Button({
//
//
//                val isNotifPermissionGranted =
//                    ContextCompat.checkSelfPermission(
//                        activity,
//                        Manifest.permission.POST_NOTIFICATIONS
//                    )
//
//                if (isNotifPermissionGranted == PackageManager.PERMISSION_GRANTED) {
//                    sendTestNotification(activity,"aa","aaa")
//                    Log.i("ASKFORPERMISSION", "PERMISSION IS ALREADY GRANTED")
//                } else {
//                    Log.i("ASKFORPERMISSION", "ASK FOR PERMISSION")
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
//                    }
//                }
//
//            }) {
//                Text("Notification")
//            }
//        }
//    }
}