package com.test.marketing.ui

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.test.marketing.AppPrefs
import com.test.marketing.MyNotificationManager.sendTestNotification
import com.test.marketing.ui.viewModel.MarketingAppViewModel
import com.test.marketing.R
import com.test.marketing.ui.model.CommentsOfApps
import com.test.marketing.ui.model.RatesOfApps
import kotlinx.serialization.json.Json

@Composable
fun AppDetailScreen(
    appId: Int,
    viewModel: MarketingAppViewModel,
    navController: NavHostController,
    activity: Activity
) {
    var app = viewModel.appList.find { it.id == appId }
    val openDialogOfRating = remember { mutableStateOf(false) }
    val openDialogOfFeedBack = remember { mutableStateOf(false) }
    val commentOfCurrentApp = remember { mutableStateOf("") }
    val commentAsString = AppPrefs.getCommentsOfApps()
    val rateOfCurrentApp = remember { mutableStateOf(0) }
    val ratesAsString = AppPrefs.getRatesOfApps()

    if (commentAsString!!.isNotEmpty()){
        val comment = Json.decodeFromString<CommentsOfApps>(commentAsString)
        when (appId) {
            0 -> commentOfCurrentApp.value = comment.app0
            1 -> commentOfCurrentApp.value = comment.app1
            2 -> commentOfCurrentApp.value = comment.app2
            3 -> commentOfCurrentApp.value = comment.app3
            4 -> commentOfCurrentApp.value = comment.app4
            5 -> commentOfCurrentApp.value = comment.app5
        }
    }
    if (ratesAsString!!.isNotEmpty()) {
        val rates = Json.decodeFromString<RatesOfApps>(ratesAsString)
        when (appId) {
            0 -> rateOfCurrentApp.value = rates.app0
            1 -> rateOfCurrentApp.value = rates.app1
            2 -> rateOfCurrentApp.value = rates.app2
            3 -> rateOfCurrentApp.value = rates.app3
            4 -> rateOfCurrentApp.value = rates.app4
            5 -> rateOfCurrentApp.value = rates.app5
        }
    }
    val textValueOfCommentSection = remember { mutableStateOf(commentOfCurrentApp.value) }
    val rateOfCurrentAppTemp = remember { mutableStateOf(rateOfCurrentApp.value) }
//    rateOfCurrentAppTemp.value=rateOfCurrentApp.value

    if (openDialogOfRating.value) {
        Dialog({
            rateOfCurrentAppTemp.value = rateOfCurrentApp.value
            openDialogOfRating.value = !openDialogOfRating.value
        }) {

            Card(
                Modifier
                    .height(200.dp)
                    .width(350.dp),
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Rate Us")
                    Spacer(Modifier.weight(1f))
                    Row {
                        IconButton({
                            rateOfCurrentAppTemp.value = 1
                        }) {
                            Icon(
                                painter = painterResource(if (rateOfCurrentAppTemp.value >= 1) R.drawable.baseline_star_rate_24 else R.drawable.outline_star),
                                contentDescription = ""
                            )
                        }
                        Spacer(Modifier.size(20.dp))
                        IconButton({
                            rateOfCurrentAppTemp.value = 2
                        }) {
                            Icon(
                                painter = painterResource(if (rateOfCurrentAppTemp.value >= 2) R.drawable.baseline_star_rate_24 else R.drawable.outline_star),
                                contentDescription = ""
                            )
                        }
                        Spacer(Modifier.size(20.dp))
                        IconButton({
                            rateOfCurrentAppTemp.value = 3
                        }) {
                            Icon(
                                painter = painterResource(if (rateOfCurrentAppTemp.value >= 3) R.drawable.baseline_star_rate_24 else R.drawable.outline_star),
                                contentDescription = ""
                            )
                        }
                        Spacer(Modifier.size(20.dp))
                        IconButton({
                            rateOfCurrentAppTemp.value = 4
                        }) {
                            Icon(
                                painter = painterResource(if (rateOfCurrentAppTemp.value >= 4) R.drawable.baseline_star_rate_24 else R.drawable.outline_star),
                                contentDescription = ""
                            )
                        }
                        Spacer(Modifier.size(20.dp))
                        IconButton({
                            rateOfCurrentAppTemp.value = 5
                        }) {
                            Icon(
                                painter = painterResource(if (rateOfCurrentAppTemp.value >= 5) R.drawable.baseline_star_rate_24 else R.drawable.outline_star),
                                contentDescription = ""
                            )
                        }
                    }
                    Button({
                        val rateOfCurrentAppAsString = AppPrefs.getRatesOfApps()
                        if (rateOfCurrentAppAsString!!.isNotEmpty()) {
                            val rateOfCurrentApps =
                                Json.decodeFromString<RatesOfApps>(rateOfCurrentAppAsString)
                            when (appId) {
                                0 -> rateOfCurrentApps.app0 = rateOfCurrentAppTemp.value
                                1 -> rateOfCurrentApps.app1 = rateOfCurrentAppTemp.value
                                2 -> rateOfCurrentApps.app2 = rateOfCurrentAppTemp.value
                                3 -> rateOfCurrentApps.app3 = rateOfCurrentAppTemp.value
                                4 -> rateOfCurrentApps.app4 = rateOfCurrentAppTemp.value
                                5 -> rateOfCurrentApps.app5 = rateOfCurrentAppTemp.value
                            }
                            AppPrefs.setRatesOfApps(Json.encodeToString(rateOfCurrentApps))
                        } else {
                            val rateOfCurrentAppJson = when (appId) {
                                0 -> RatesOfApps(rateOfCurrentAppTemp.value, 0, 0, 0, 0, 0)
                                1 -> RatesOfApps(0, rateOfCurrentAppTemp.value, 0, 0, 0, 0)
                                2 -> RatesOfApps(0, 0, rateOfCurrentAppTemp.value, 0, 0, 0)
                                3 -> RatesOfApps(0, 0, 0, rateOfCurrentAppTemp.value, 0, 0)
                                4 -> RatesOfApps(0, 0, 0, 0, rateOfCurrentAppTemp.value, 0)
                                5 -> RatesOfApps(0, 0, 0, 0, 0, rateOfCurrentAppTemp.value)
                                else -> RatesOfApps() // Default case (all zeros)
                            }.let { Json.encodeToString(it) }

                            AppPrefs.setRatesOfApps(rateOfCurrentAppJson)

                        }
                        Toast.makeText(activity, "saved!!!", Toast.LENGTH_SHORT)
                            .show()
                        openDialogOfRating.value = !openDialogOfRating.value
                    }) {
                        Text("Submit")
                    }
                }
            }
        }
    }
    if (openDialogOfFeedBack.value) {
        Dialog({
            textValueOfCommentSection.value = commentOfCurrentApp.value
            openDialogOfFeedBack.value = !openDialogOfFeedBack.value
        }) {
            Card(
                Modifier
                    .height(300.dp)
                    .width(500.dp),
            ) {

                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Give Us FeedBack")
                    Spacer(Modifier.size(20.dp))
                    TextField(value = textValueOfCommentSection.value, onValueChange = {textValueOfCommentSection.value=it})
                    Spacer(Modifier.weight(1f))
                    Button({
                        val commentsOfAppsAsString = AppPrefs.getCommentsOfApps()
                        if (commentsOfAppsAsString!!.isNotEmpty()){
                            val commentsOfApps = Json.decodeFromString<CommentsOfApps>(commentsOfAppsAsString)
                            when (appId) {
                                0 -> commentsOfApps.app0 = textValueOfCommentSection.value
                                1 -> commentsOfApps.app1 = textValueOfCommentSection.value
                                2 -> commentsOfApps.app2 = textValueOfCommentSection.value
                                3 -> commentsOfApps.app3 = textValueOfCommentSection.value
                                4 -> commentsOfApps.app4 = textValueOfCommentSection.value
                                5 -> commentsOfApps.app5 = textValueOfCommentSection.value
                            }
                            AppPrefs.setCommentsOfApps(Json.encodeToString(commentsOfApps))
                        }else{
                            val commentOfCurrentAppJson = when (appId) {
                                0 -> CommentsOfApps(textValueOfCommentSection.value, "", "", "", "", "")
                                1 -> CommentsOfApps( "",textValueOfCommentSection.value, "", "", "", "")
                                2 -> CommentsOfApps( "", "",textValueOfCommentSection.value, "", "", "")
                                3 -> CommentsOfApps( "", "", "",textValueOfCommentSection.value, "", "")
                                4 -> CommentsOfApps( "", "", "", "",textValueOfCommentSection.value, "")
                                5 -> CommentsOfApps( "", "", "", "", "",textValueOfCommentSection.value)
                                else -> CommentsOfApps() // Default case (all zeros)
                            }.let { Json.encodeToString(it) }

                            AppPrefs.setCommentsOfApps(commentOfCurrentAppJson)
                        }
                        Toast.makeText(activity, "saved!!!", Toast.LENGTH_SHORT)
                            .show()
                        openDialogOfFeedBack.value = !openDialogOfFeedBack.value
                    }) {
                        Text("Submit")
                    }
                }
            }
        }
    }
    BackHandler {
        AppPrefs.setAppCurrentId(-1)
        navController.navigate(AppsScreen.AppsScreen.name)
    }
    Column(Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = app?.imageUrl, contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = Color.Blue)
                .clickable(onClick = {
                    openDialogOfRating.value = !openDialogOfRating.value
                }),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Rate the App:")
            Spacer(Modifier.size(40.dp))
            Icon(
                painter = painterResource(if (rateOfCurrentAppTemp.value >= 1) R.drawable.baseline_star_rate_24 else R.drawable.outline_star),
                contentDescription = ""
            )
            Spacer(Modifier.size(20.dp))
            Icon(
                painter = painterResource(if (rateOfCurrentAppTemp.value >= 2) R.drawable.baseline_star_rate_24 else R.drawable.outline_star),
                contentDescription = ""
            )
            Spacer(Modifier.size(20.dp))
            Icon(
                painter = painterResource(if (rateOfCurrentAppTemp.value >= 3) R.drawable.baseline_star_rate_24 else R.drawable.outline_star),
                contentDescription = ""
            )
            Spacer(Modifier.size(20.dp))
            Icon(
                painter = painterResource(if (rateOfCurrentAppTemp.value >= 4) R.drawable.baseline_star_rate_24 else R.drawable.outline_star),
                contentDescription = ""
            )
            Spacer(Modifier.size(20.dp))
            Icon(
                painter = painterResource(if (rateOfCurrentAppTemp.value >= 5) R.drawable.baseline_star_rate_24 else R.drawable.outline_star),
                contentDescription = ""
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color.LightGray)
                .clickable(onClick = {
                    openDialogOfFeedBack.value = !openDialogOfFeedBack.value
                })
                .padding(20.dp)
                ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Give Us Your FeedBack:")
            Spacer(Modifier.size(40.dp))
            Text(
                if (commentOfCurrentApp.value!="") commentOfCurrentApp.value else "no comment for this product yet!",
                textAlign = TextAlign.Justify
            )
            Spacer(Modifier.size(80.dp))
            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission Accepted: Do something
                    Log.i("ASKFORPERMISSION", "GOT PERMISSION")
                    sendTestNotification(activity,app!!.name,app.description,app.id)
                } else {
                    // Permission Denied: Do something
                    Log.i("ASKFORPERMISSION", "DID NOT GET PERMISSION")
                    Toast.makeText(activity, "DID NOT GET PERMISSION", Toast.LENGTH_SHORT).show()
                }
            }
            Button({


                val isNotifPermissionGranted =
                    ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.POST_NOTIFICATIONS
                    )

                if (isNotifPermissionGranted == PackageManager.PERMISSION_GRANTED) {
                    sendTestNotification(activity,app!!.name,app.description,app.id)
                    Log.i("ASKFORPERMISSION", "PERMISSION IS ALREADY GRANTED")
                } else {
                    Log.i("ASKFORPERMISSION", "ASK FOR PERMISSION")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }

            }) {
                Text("Notification")
            }
        }
    }
}

