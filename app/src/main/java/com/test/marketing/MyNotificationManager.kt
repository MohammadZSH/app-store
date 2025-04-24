package com.test.marketing

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.test.marketing.R

object MyNotificationManager {

    private const val TEST_NOTIFICATION_CHANNEL = "APP_DETAILS_NOTIFICATION_CHANNEL3"


    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun sendTestNotification(context: Context,appTitle: String,content: String,id: Int) {
        createNotificationChannel(context)
        val openAppIntent = Intent(context, MainActivity::class.java)
        openAppIntent.putExtra("app_id",id)
        val myPendingIntent = PendingIntent.getActivity(context,0,openAppIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val notifBuilder = NotificationCompat.Builder(context, TEST_NOTIFICATION_CHANNEL)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(appTitle)
            .setContentText(content)
            .setContentIntent(myPendingIntent)
            .setAutoCancel(true)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.shopping_cart))
        NotificationManagerCompat.from(context).notify(id, notifBuilder.build())
    }



    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "APP DETAILS CHANNEL"
            val descriptionText = "CHANNEL DESCRIPTION"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(TEST_NOTIFICATION_CHANNEL, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}