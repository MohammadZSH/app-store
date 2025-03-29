package com.test.marketing.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.test.marketing.ui.viewModel.MarketingAppViewModel

@Composable
fun AppDetailScreen(appId: Int,viewModel: MarketingAppViewModel) {
    var app = viewModel.appList.find { it.id == appId }
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