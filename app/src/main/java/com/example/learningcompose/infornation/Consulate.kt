package com.example.learningcompose.infornation

data class Consulate(
    val name:String,
    val address:String,
    val phoneNumbers:List<String>,
    val imageResId: Int,   // Rasm (drawable dan)
    val latitude:Double,
    val longitude:Double,
    val distance:Float?=null,
    val description: String
)
data class NewCon(
    val name:String,
    val address:String,
    val phoneNumbers:List<String>,
    val imageResId: Int,   // Rasm (drawable dan)
    val latitude:Double,
    val longitude:Double,
    val distance:Float,
    val description: String
)
