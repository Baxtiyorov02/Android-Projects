package com.example.learningcompose.location

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class LocationApp: Application()  {


    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate() {
        super.onCreate()

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel(
                channelId,
                "Location",
                NotificationManager.IMPORTANCE_HIGH
            )

            val notificationManager
            =getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
   companion object {
        const val channelId = "location_notification_channel"
    }


}
