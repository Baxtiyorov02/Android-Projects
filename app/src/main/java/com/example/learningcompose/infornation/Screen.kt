package com.example.learningcompose.infornation

sealed class Screen(val route: String) {
    object List : Screen("consulate_list")
    object Detail : Screen("consulate_detail")

}