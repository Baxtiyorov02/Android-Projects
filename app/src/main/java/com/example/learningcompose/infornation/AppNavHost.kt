package com.example.learningcompose.infornation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppNavHost(navController: NavHostController,viewModel: ConsulateViewModel){

    NavHost(navController = navController, startDestination = Screen.List.route) {
        composable(Screen.List.route) {
            ConsulateListScreen(viewModel.getConsulates() ) { selected ->
                viewModel.selectConsulate(selected)
                navController.navigate(Screen.Detail.route)
            }
        }

        composable(Screen.Detail.route) {
            viewModel.selectedConsulate.value?.let { consulate ->
                ConsulateDetailScreen(consulate)
            }
        }
    }
}