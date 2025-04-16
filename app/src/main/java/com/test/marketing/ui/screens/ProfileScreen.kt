package com.test.marketing.ui.screens

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.test.marketing.AppPrefs
import com.test.marketing.LoginActivity
import com.test.marketing.MainActivity
import com.test.marketing.ProfileActivity
import com.test.marketing.R
import com.test.marketing.ui.AppsScreen
import com.test.marketing.ui.User
import com.test.marketing.ui.theme.MarketingAppColors
import com.test.marketing.ui.viewModel.MarketingAppViewModel
import kotlinx.serialization.json.Json

@Composable
fun ProfileScreen(
    viewModel: MarketingAppViewModel,
    navController: NavHostController,
    activity: Activity
) {
    var appCurrentId = AppPrefs.getAppCurrentId()
    BackHandler {
        AppPrefs.setAppCurrentId(-1)
        appCurrentId = -1
        navController.navigate(AppsScreen.AppsScreen.name)
        viewModel.isTopAppBarState.value = true
    }
    val userAsString = AppPrefs.getUser()
    val user = Json.decodeFromString<User>(userAsString!!)
    Column(
        Modifier
            .fillMaxSize()
            .background(if (user.isPremium) MarketingAppColors.PremiumUsersColor else MarketingAppColors.NormalUsersColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 15.dp, vertical = 7.dp)
                .background(
                    Color.Transparent,
                    RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
                )
        ) {
            IconButton({
                AppPrefs.setAppCurrentId(-1)
                appCurrentId = -1
                navController.navigate(AppsScreen.AppsScreen.name)
                viewModel.isTopAppBarState.value = true
            }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
            }
            Spacer(Modifier.weight(1f))
            Text("Profile", fontSize = 30.sp)
            Spacer(Modifier.weight(1.35f))
        }

        Spacer(Modifier.size(60.dp))
        Text("Name: ${user.name}", fontSize = 25.sp)
        Spacer(Modifier.size(40.dp))
        Text("Age: ${user.age}", fontSize = 25.sp)
        Spacer(Modifier.size(40.dp))
        Text("Phone Number: ${user.phoneNumber}", fontSize = 25.sp)
        Spacer(Modifier.size(40.dp))
        Text("Email: ${user.email}", fontSize = 25.sp)
        Spacer(Modifier.size(40.dp))
        Text(
            "User State: ${if (user.isPremium) "Premium User" else "Normal User"}",
            fontSize = 25.sp
        )
        Spacer(Modifier.size(80.dp))
        Button(
            {
                val intentToProfileActivity = Intent(activity, ProfileActivity::class.java)
                activity.startActivity(intentToProfileActivity)
                activity.finish()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Edit")
        }
        Spacer(Modifier.size(30.dp))
        Button(
            {
                val intentToLoginActivity = Intent(activity, LoginActivity::class.java)
                activity.startActivity(intentToLoginActivity)
                activity.finish()
                AppPrefs.setUser("")
            },
            colors = ButtonDefaults.buttonColors(containerColor = MarketingAppColors.LogOutButtonColor)
        ) {
            Text("Log Out")
        }
    }
}