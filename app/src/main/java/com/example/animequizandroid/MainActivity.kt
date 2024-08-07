package com.example.animequizandroid


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.animequizandroid.ui.navigation.AnimeQuizApp
import com.example.animequizandroid.ui.screens.AnimeQuizViewModel
import com.example.animequizandroid.ui.theme.AnimeQuizAndroidTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            AnimeQuizAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = getViewModel<AnimeQuizViewModel>()
                    AnimeQuizApp(animeQuizViewModel = viewModel)
                }
            }
        }
    }
}

