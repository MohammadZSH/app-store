package com.test.marketing.ui.model

import com.test.marketing.AppPrefs

data class App(
    val id : Int,
    val name: String,
    val description:String,
    val imageUrl: String,
    val rate: Float,
    val price: Int,
    var feedBack:String=""
)
