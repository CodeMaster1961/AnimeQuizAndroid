package com.example.animequizandroid.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animequizandroid.data.dtos.Question
import com.example.animequizandroid.data.dtos.getOptions

@Composable
fun AnimeQuizScreen(
    viewModel: AnimeQuizViewModel
) {
    val questions by viewModel.questions.collectAsState()
    val currentQuestionIndex by viewModel.currentQuestionIndex.collectAsState()
    QuizQuestionDisplay(question = questions[currentQuestionIndex], viewModel = viewModel, {})
}

@Composable
fun QuizQuestionDisplay(
    question: Question,
    viewModel: AnimeQuizViewModel,
    onNextClick: () -> Unit
) {
    var isClicked by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = question.question)
        AsyncImage(model = question.image, contentDescription = "")
        AnswerButtons(
            answers = question.getOptions(),
            viewModel,
        )

        Button(onClick = {
            isClicked = true
        }) {
            if (isClicked) {
                Text(text = "Next")
                onNextClick()
            } else {
                Text(text = "Submit")
            }
        }
    }
}

@Composable
fun AnswerButtons(answers: List<String>, viewModel: AnimeQuizViewModel) {

    answers.forEachIndexed { index, answer ->
        AnswerButton(
            viewModel = viewModel,
            answer = answer,
            onClick = {
                viewModel.selectedAnswer(index)
            },
            isSelected = viewModel.isSelected == index,
        )
    }
}

@Composable
fun AnswerButton(
    viewModel: AnimeQuizViewModel,
    answer: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {

    Button(
        onClick = onClick,
        border = BorderStroke(2.dp, viewModel.borderColor(isSelected)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray
        )
    ) {
        Text(text = answer)
    }
}