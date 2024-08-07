package com.example.animequizandroid.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

@Composable
fun ResultScreen(viewModel: AnimeQuizViewModel) {
    val score by viewModel.correctAnsweredPoints.collectAsState()
    val totalQuestions by viewModel.questions.collectAsState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = viewModel.username)
        Text(text = "Your score is $score out of ${totalQuestions.size}")
    }
}