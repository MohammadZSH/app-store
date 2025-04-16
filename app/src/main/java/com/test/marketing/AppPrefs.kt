package com.test.marketing

import android.content.Context
import android.content.SharedPreferences
import com.test.marketing.ui.App

object AppPrefs {
    private const val APP_ID_KEY ="APP_ID"
    private lateinit var preferences: SharedPreferences

    fun setUpAppPrefs(activity: App){
       preferences= activity.getSharedPreferences("settings",Context.MODE_PRIVATE)
    }
    //##############################
    fun setUser (userAsString:String){
        preferences.edit().putString("USER_AS_STRING",userAsString).commit()
    }
    fun getUser(): String?{
        return preferences.getString("USER_AS_STRING","")
    }

    //##############################
    fun setAppCurrentId (appCurrentId: Int){
        preferences.edit().putInt(APP_ID_KEY,appCurrentId).commit()
    }
    fun getAppCurrentId(): Int?{
        return preferences.getInt(APP_ID_KEY,-1)
    }
    //##############################
    fun setRatesOfApps (ratesOfApp: String){
        preferences.edit().putString("RATES_OF_APPS",ratesOfApp).commit()
    }
    fun getRatesOfApps(): String?{
        return preferences.getString("RATES_OF_APPS","")
    }
    //##############################
    fun setCommentsOfApps (commentsOfApp: String){
        preferences.edit().putString("COMMENTS_OF_APPS",commentsOfApp).commit()
    }
    fun getCommentsOfApps(): String?{
        return preferences.getString("COMMENTS_OF_APPS","")
    }

}