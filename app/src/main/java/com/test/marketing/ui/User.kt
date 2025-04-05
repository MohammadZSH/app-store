package com.test.marketing.ui

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var name: String="",
    var phoneNumber: String,
    var email: String="",
    var age: String="",
    var isPremium: Boolean=false
)