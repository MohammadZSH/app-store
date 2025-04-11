package com.test.marketing.ui

import android.app.Application
import com.test.marketing.AppPrefs

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppPrefs.setUpAppPrefs(this)
    }
}