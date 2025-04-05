package com.test.marketing.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.test.marketing.ui.viewModel.MarketingAppViewModel
import com.test.marketing.R
import com.test.marketing.ui.AppsScreen
import com.test.marketing.ui.theme.MarketingAppColors

@Composable
fun TopAppBar(viewModel: MarketingAppViewModel, navController: NavHostController) {
    val interactionSourceOfProfileStats by remember { mutableStateOf(MutableInteractionSource()) }
    val textFieldOfTopView by viewModel.textFieldOfTopView.collectAsState()
    BackHandler {
        viewModel.isTopAppBarState.value = true
        navController.navigate(AppsScreen.AppsScreen.name)
    }
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .height(120.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .size(55.dp)
                .background(MarketingAppColors.TopAppBarProfileCircle, shape = CircleShape)
                .clickable(
                    onClick = {
                        viewModel.isTopAppBarState.value=false
                        navController.navigate(AppsScreen.Profile.name)
                    }, interactionSource = interactionSourceOfProfileStats,
                    indication = rememberRipple(
                        bounded = true,
                        radius = 27.dp

                    )

                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painterResource(R.drawable.outline_person_outline_24),
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(Modifier.weight(1f))
        TextField(
            textStyle = TextStyle.Default,
            modifier = Modifier
                .width(308.dp)
                .height(60.dp),
            value = textFieldOfTopView,
            onValueChange = { viewModel.textFieldOfTopView.value = it },
            placeholder = { Text("جستجو در بازی ها و برنامه ها", fontSize = 15.sp) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_keyboard_voice_24),
                    contentDescription = ""
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_search_24),
                    contentDescription = ""
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedLeadingIconColor = MarketingAppColors.TopAppBarTextFieldContainer,
                unfocusedLeadingIconColor = MarketingAppColors.TopAppBarTextFieldContainer,
                focusedPlaceholderColor = MarketingAppColors.TopAppBarTextFieldContainer,
                unfocusedPlaceholderColor = MarketingAppColors.TopAppBarTextFieldContainer,
                focusedTrailingIconColor = MarketingAppColors.TopAppBarTextFieldContainer,
                unfocusedTrailingIconColor = MarketingAppColors.TopAppBarTextFieldContainer,

                ),
            shape = RoundedCornerShape(10.dp)
        )
    }
}