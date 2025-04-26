package com.test.marketing

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var currentCancelCount = AppPrefs.getCancelCount()
        Log.i("cancelCount","counter : " + currentCancelCount)
        currentCancelCount = currentCancelCount!! + 1
        AppPrefs.setCancelCount(currentCancelCount)
    }
}