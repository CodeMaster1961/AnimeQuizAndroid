package com.example.animequizandroid.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animequizandroid.R


@Composable
fun StartScreen(
    viewModel: AnimeQuizViewModel,
    navigateUp: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.aestethic), contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        WelcomeCard(
            inputValue = viewModel.username,
            onValueChange = {
                viewModel.username = it
            },
            viewModel = viewModel,
            onClick = { navigateUp() }
        )
        Text(
            text = "Anime Quiz", fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun WelcomeCard(
    inputValue: String,
    onValueChange: (String) -> Unit,
    viewModel: AnimeQuizViewModel,
    onClick: () -> Unit
) {
    Card(
        shape = ShapeDefaults.Medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(end = 10.dp, start = 10.dp)
    ) {
        Text(
            text = "Welcome",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp)
        )
        Text(
            text = "Please enter your name",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 5.dp)
        )
        StartQuizForm(
            inputValue = inputValue,
            onValueChange = onValueChange,
            viewModel.validateUsername(),
            onClick
        )
    }
}

@Composable
fun StartQuizForm(
    inputValue: String,
    onValueChange: (String) -> Unit,
    isButtonEnabled: Boolean,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        OutlinedTextField(
            value = inputValue, onValueChange = onValueChange,
            label = { Text(text = "Username") },
            modifier = Modifier.padding(bottom = 10.dp)
        )
        SubmitButton(onClick, isButtonEnabled)
    }
}

@Composable
fun SubmitButton(
    onSubmit: () -> Unit,
    isButtonEnabled: Boolean
) {
    Button(
        onClick = { onSubmit() }, modifier = Modifier.width(290.dp),
        shape = RoundedCornerShape(10.dp),
        enabled = isButtonEnabled
    ) {
        Text(text = "Start")
    }
}