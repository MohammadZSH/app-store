package com.test.marketing.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.test.marketing.ui.model.App
import com.test.marketing.ui.viewModel.MarketingAppViewModel

@Composable
fun AppsScreen(viewModel: MarketingAppViewModel, control: (App) -> Unit) {
    val selectedApp by viewModel.selectedApp.collectAsState()
    val appList = viewModel.appList
    val featuredAppList = viewModel.featuredAppList

    Column {

        AnimatedContent(selectedApp.imageUrl) {
            Box(contentAlignment = Alignment.BottomCenter) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    AsyncImage(
                        model = it, contentDescription = "", contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                    )
                }
                Row(
                    Modifier
                        .padding(35.dp)
                        .width(80.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    featuredAppList.forEachIndexed { index, _ ->
                        Box(
                            Modifier
                                .size(10.dp)
                                .background(
                                    if (viewModel.selectedFeaturedIndex == index) {
                                        Color.White.copy(alpha = 1f)
                                    } else {
                                        Color.White.copy(alpha = 0.2f)
                                    }, shape = CircleShape
                                )
                        )
                        Log.i(
                            "tagg",
                            "viewModel.selectedFeaturedIndex${viewModel.selectedFeaturedIndex}  index${index}"
                        )
                    }
                }
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(3), contentPadding = PaddingValues(20.dp)) {
            items(appList.size) {
                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .width(80.dp)
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    Column (Modifier.fillMaxSize()){
                        AsyncImage(
                            model = appList[it].imageUrl,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(70.dp)

                                .clickable(onClick = {
//                                viewModel.selectedApp.value = it
                                    control(appList[it])
                                })
                        )
                        Row (){
                            Text(appList[it].name, fontSize = 16.sp)
                            Spacer(Modifier.weight(1f))
                            Text(appList[it].rate.toString(), fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}