package com.test.marketing.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class App(
    val id : Int,
    val name: String,
    val description:String,
    val imageUrl: String,
    val rate: Float,
    val price: Int,
    var feedBack:String=""
)
