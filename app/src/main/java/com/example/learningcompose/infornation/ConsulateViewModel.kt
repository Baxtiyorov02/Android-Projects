package com.example.learningcompose.infornation

import android.location.Location
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningcompose.manager.AppLocationManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ConsulateViewModel: ViewModel() {

    private val locationManager= AppLocationManager()


    private val _selectedConsulate = mutableStateOf<Consulate?>(null)
    val selectedConsulate: State<Consulate?> = _selectedConsulate

    private val _consulates = mutableStateOf<List<Consulate>>(emptyList())
    val consulates: State<List<Consulate>> = _consulates


    init {
        loadConsulates()
    }



    // LocationFlow ni doimiy ravishda kuzatish
    private fun loadConsulates() {
             viewModelScope.launch {

            println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy")
            locationManager.locationFlow.collect{ myLocation ->
                  println("xxxxxxxxxxxxxxx: $myLocation")
                // Konsullalar ro'yxatini yangilash
                val updatedList = consulateList.map { consulate ->

                     val distance = calculateDistance(
                         myLocation.first,
                         myLocation.second,
                         consulate.latitude,
                         consulate.longitude
                     )
                    consulate.copy(distance = distance)  // Masofani hisoblash

                 }.toMutableList()

                // Yangilangan ro'yxatni saqlash
                _consulates.value = updatedList
            }
        }

    }

    private fun calculateDistance(
        startLat: Double, startLng: Double,
        endLat: Double, endLng: Double
    ): Float {
        val results = FloatArray(1)
        Location.distanceBetween(startLat, startLng, endLat, endLng, results)
        return results[0]
    }

    // Konsullar ro'yxatini olish
    fun getConsulates(): List<Consulate> = consulates.value


    // Tanlangan konsullani belgilash
    fun selectConsulate(consulate: Consulate) {
        _selectedConsulate.value = consulate
    }
}




