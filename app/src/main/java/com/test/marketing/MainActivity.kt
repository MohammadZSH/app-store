package com.test.marketing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.marketing.ui.screens.MainScreen
import com.test.marketing.ui.theme.MarketingTheme
import com.test.marketing.ui.viewModel.MarketingAppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MarketingAppViewModel = viewModel()
            MarketingTheme {
                Surface() {
                    MainScreen(viewModel, LocalActivity.current!!)
                }
            }
        }
    }
}
