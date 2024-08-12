package com.example.animequizandroid.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animequizandroid.R

@Composable
fun ResultScreen(
    viewModel: AnimeQuizViewModel,
    navigateToHome: () -> Unit
) {
    val score by viewModel.correctAnsweredPoints.collectAsState()
    val totalQuestions by viewModel.questions.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.anime), contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Your score is $score out of ${totalQuestions.size}",
            fontWeight = FontWeight.Black,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 80.dp)
        )
        Text(
            text = viewModel.username,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
        )
        BackToStartButton(navigateToHome)
        TrophyImage()
    }
}

@Composable
fun TrophyImage() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_trophy), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(300.dp),
            alignment = Alignment.TopCenter
        )
    }
}

@Composable
fun BackToStartButton(navigateToHome: () -> Unit) {
    Button(
        onClick = { navigateToHome() },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.width(260.dp)
    ) {
        Text(text = "Back to main menu")
    }
}