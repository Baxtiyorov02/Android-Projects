package com.example.learningcompose.manager

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AppLocationManager {
    val _locationFlow= MutableSharedFlow<Pair<Double,Double>>(replay = 1)
    val locationFlow=_locationFlow.asSharedFlow()


    suspend fun updateLocation(latitude:Double,longitude:Double){
        _locationFlow.emit(Pair(latitude,longitude))

    }
}