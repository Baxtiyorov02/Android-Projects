package com.example.learningcompose.infornation

data class Consulate(
    val name:String,
    val address:String,
    val phoneNumbers:List<String>,
    val imageResId: Int,   // Rasm (drawable dan)
    val description: String
)
