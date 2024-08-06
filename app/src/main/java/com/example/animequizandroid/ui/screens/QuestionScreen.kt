package com.example.animequizandroid.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.animequizandroid.data.dtos.Question
import com.example.animequizandroid.data.dtos.getOptions

@Composable
fun AnimeQuizScreen(
    viewModel: AnimeQuizViewModel
) {
    val questions by viewModel.questions.collectAsState()
    val currentQuestionIndex by viewModel.currentQuestionIndex.collectAsState()
    QuizQuestionDisplay(
        question = questions[currentQuestionIndex],
        viewModel = viewModel,
        onNextClick = {
            viewModel.nextQuestion()
        })
}

@Composable
fun QuizQuestionDisplay(
    question: Question,
    viewModel: AnimeQuizViewModel,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        QuestionText(questionText = question.question)
        AsyncImage(model = question.image, contentDescription = "")
        AnswerButtons(
            answers = question.getOptions(),
            viewModel
        )
        SubmitAnswerButton(
            viewModel,
            question.correctAnswer,
            onNextClick
        )
    }
}

@Composable
fun QuestionText(questionText: String) {
    Text(
        text = questionText,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun SubmitAnswerButton(
    viewModel: AnimeQuizViewModel,
    isCorrectAnswer: Int,
    onNextClick: () -> Unit
) {
    Button(
        onClick = {
            viewModel.submitAnswer(isCorrectAnswer)
            onNextClick()
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp, start = 10.dp, top = 10.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        if (viewModel.isAnswerSubmitted) Text(text = "Next") else Text(text = "Submit")
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
            containerColor = viewModel.showSubmittedAnswerResults(index, Color.White),
            disabledContainerColor = viewModel.showSubmittedAnswerResults(index, Color.Gray)
        )
    }
}

@Composable
fun AnswerButton(
    viewModel: AnimeQuizViewModel,
    answer: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    containerColor: Color = Color.White,
    disabledContainerColor: Color
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, viewModel.borderColor(isSelected)),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = Color.Black,
            disabledContainerColor = disabledContainerColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp, start = 10.dp, top = 10.dp),
        enabled = !viewModel.isAnswerSubmitted
    ) {
        Text(text = answer, color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}