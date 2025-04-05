package com.test.marketing

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object AppPrefs {
    private lateinit var preferences: SharedPreferences

    fun setUpAppPrefs(activity: Activity){
       preferences= activity.getSharedPreferences("settings",Context.MODE_PRIVATE)
    }
    //##############################
    fun setUser (userAsString:String){
        preferences.edit().putString("USER_AS_STRING",userAsString).apply()
    }
    fun getUser(): String?{
        return preferences.getString("USER_AS_STRING","")
    }

    //##############################
    fun setAppCurrentId (appCurrentId: Int){
        preferences.edit().putInt("APP_ID",appCurrentId).commit()
    }
    fun getAppCurrentId(): Int?{
        return preferences.getInt("APP_ID",-1)
    }
    //##############################
    fun setScoreOfBestPlayer (scoreOfBestPlayer: Int){
        preferences.edit().putInt("NAME_OF_LATEST_PLAYER",scoreOfBestPlayer).commit()
    }
    fun getScoreOfBestPlayer(): Int?{
        return preferences.getInt("NAME_OF_LATEST_PLAYER",0)
    }
    //##############################
    fun setNumberOfCounter (numberOfCounter: Int){
        preferences.edit().putInt("NAME_OF_LATEST_PLAYER",numberOfCounter).commit()
    }
    fun getNumberOfCounter (): Int?{
        return preferences.getInt("NAME_OF_LATEST_PLAYER",0)
    }
    //##############################


}