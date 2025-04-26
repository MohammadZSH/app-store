package com.test.marketing.ui.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.test.marketing.AppPrefs
import com.test.marketing.ui.AppRepository
import com.test.marketing.ui.model.App
import com.test.marketing.ui.model.RatesOfApps
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.json.Json
import java.util.Timer
import java.util.TimerTask

class MarketingAppViewModel : ViewModel() {
    val isTopAppBarState = MutableStateFlow(true)
    val textFieldOfTopView = MutableStateFlow("")
    val appList = AppRepository.appList
    private val featuredAppIdList = listOf(
        3, 4, 2, 1
    )
    var featuredAppList: List<App>

    var selectedApp: MutableStateFlow<App>

    init {
        featuredAppList = AppRepository.appList.filter { featuredAppIdList.contains(it.id) }
        selectedApp = MutableStateFlow(featuredAppList.first())
        autoChangeApp()
    }

    var selectedFeaturedIndex = -1
    private fun autoChangeApp() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                selectedFeaturedIndex++
                if (selectedFeaturedIndex >= featuredAppList.size) {
                    selectedFeaturedIndex = 0
                }
                selectedApp.value = featuredAppList[selectedFeaturedIndex]
            }
        }, 0, 2000)
    }
}