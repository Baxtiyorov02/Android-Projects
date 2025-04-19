package com.example.learningcompose.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class DefaultLocationClient(
    private val context: Context,
    private val client: FusedLocationProviderClient
) : LocationClient {

    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(interval: Long): Flow<Location> {
        return callbackFlow {
            if (!context.hasLocationPermission()) {
                throw LocationClient.LocationException("Missing locationpermission")
            }
            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGPSEnaled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isInternetEnabled =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!isGPSEnaled && !isInternetEnabled) {
                throw LocationClient.LocationException("Gps is disabled")
            }

            val request = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // API 31+ (Android 12) uchun
                LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, interval)
                    .setMinUpdateIntervalMillis(interval)
                    .build()
            } else {
                // Eski API’lar uchun (deprekat bo‘lgan, lekin ishlaydi)
                @Suppress("DEPRECATION")
                LocationRequest.create().apply {
                    this.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                    this.interval = interval
                }
            }

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
                        launch { send(location) }

                    }
                }
            }
            client.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )
            awaitClose {
                client.removeLocationUpdates(locationCallback)
            }
        }


    }

}

