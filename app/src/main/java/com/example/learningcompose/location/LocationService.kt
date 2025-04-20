package com.example.learningcompose.location

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.core.app.NotificationCompat
import com.example.learningcompose.R
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LocationService:Service() {

    private val serviceScope= CoroutineScope(SupervisorJob()+Dispatchers.IO)
    private lateinit var locationClient: LocationClient

    override fun onBind(p0: Intent?): IBinder? {
        return  null
    }

    override fun onCreate() {
        super.onCreate()

        locationClient= DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent?.action){
            ACTION_START ->start()
            ACTION_STOP ->stop()

        }
            return super.onStartCommand(intent, flags, startId)


    }

    private fun start() {
        val deviceModel = Build.MODEL
        val manufacturer = Build.MANUFACTURER
        val androidVersion = Build.VERSION.RELEASE
        val sdkInt = Build.VERSION.SDK_INT
        val android_id=Settings.Secure.getString(applicationContext.contentResolver,Settings.Secure.ANDROID_ID)


        val notification=NotificationCompat.Builder(this, LocationApp.channelId)
            .setContentTitle("Tracking location...")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.baseline_notifications_active_24)
            .setOngoing(true)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(
                    "Device:$manufacturer $deviceModel\n" +
                    "Android: $androidVersion (SDK $sdkInt)\n"+
                    "Model: $deviceModel\n"+
                    "Android_id: $android_id"
                )
            )


        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient.getLocationUpdates(60000L).
                catch { e -> e.printStackTrace() }
            .onEach { location->
                val lag=location.latitude.toString()
                val long=location.longitude.toString()
                val updateNotification=notification.setContentTitle(
                    "Location: ($lag,$long)"
                )

                notificationManager.notify(1,updateNotification.build())
            }.launchIn(serviceScope)

        startForeground(1,notification.build())

                }



    private fun stop() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            // Android 14 (API 34) va undan yuqori
            stopForeground(STOP_FOREGROUND_REMOVE)
        } else {
            // Eski usul
            @Suppress("DEPRECATION")
            stopForeground(true)
        }
        stopSelf()
    }


    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }


    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}