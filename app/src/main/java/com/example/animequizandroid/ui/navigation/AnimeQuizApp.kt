package com.example.animequizandroid.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animequizandroid.ui.screens.AnimeQuizScreen
import com.example.animequizandroid.ui.screens.AnimeQuizViewModel
import com.example.animequizandroid.ui.screens.ResultScreen
import com.example.animequizandroid.ui.screens.StartScreen

@Composable
fun AnimeQuizApp(
    navController: NavHostController = rememberNavController(),
    animeQuizViewModel: AnimeQuizViewModel
) {
    NavHost(navController = navController, startDestination = AppScreens.START_SCREEN.name) {
        composable(route = AppScreens.START_SCREEN.name) {
            StartScreen(viewModel = animeQuizViewModel,
                navigateUp = {
                    navController.navigate(AppScreens.QUIZ_SCREEN.name)
                })
        }
        composable(AppScreens.QUIZ_SCREEN.name) {
            AnimeQuizScreen(viewModel = animeQuizViewModel, navigateUp = {
                navController.navigate(AppScreens.RESULT_SCREEN.name)
            })
        }

        composable(AppScreens.RESULT_SCREEN.name) {
            ResultScreen(viewModel = animeQuizViewModel,
                navigateToHome = {
                    navController.navigate(AppScreens.START_SCREEN.name)
                })
        }
    }
}