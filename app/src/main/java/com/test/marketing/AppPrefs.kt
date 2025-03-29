package com.test.marketing

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import java.util.prefs.Preferences

object AppPrefs {
    private lateinit var preferences: SharedPreferences

    fun setUpAppPrefs(activity: Activity){
       preferences= activity.getSharedPreferences("settings",Context.MODE_PRIVATE)
    }
    //##############################
    fun setNameOfLatestPlayer (nameOfLatestPlayer:String){
        preferences.edit().putString("NAME_OF_LATEST_PLAYER",nameOfLatestPlayer)
    }
    fun getNameOfLatestPlayer(): String?{
        return preferences.getString("NAME_OF_LATEST_PLAYER","")
    }

    //##############################
    fun setNameOfBestPlayer (nameOfBestPlayer:String){
        preferences.edit().putString("NAME_OF_BEST_PLAYER",nameOfBestPlayer)
    }
    fun getNameOfBestPlayer(): String?{
        return preferences.getString("NAME_OF_BEST_PLAYER","")
    }
    //##############################
    fun setScoreOfBestPlayer (scoreOfBestPlayer: Int){
        preferences.edit().putInt("NAME_OF_LATEST_PLAYER",scoreOfBestPlayer)
    }
    fun getScoreOfBestPlayer(): Int?{
        return preferences.getInt("NAME_OF_LATEST_PLAYER",0)
    }
    //##############################
    fun setNumberOfCounter (numberOfCounter: Int){
        preferences.edit().putInt("NAME_OF_LATEST_PLAYER",numberOfCounter)
    }
    fun getNumberOfCounter (): Int?{
        return preferences.getInt("NAME_OF_LATEST_PLAYER",0)
    }
    //##############################


}