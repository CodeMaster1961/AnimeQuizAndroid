package com.example.animequizandroid.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.animequizandroid.ui.screens.StartScreen

@Composable
fun AnimeQuizApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start_screen") {

    }
}