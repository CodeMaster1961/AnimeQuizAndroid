package com.example.animequizandroid.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animequizandroid.data.dtos.Question
import com.example.animequizandroid.data.repository.AnimeQuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeQuizViewModel(
    private val animeQuizRepository: AnimeQuizRepository
) : ViewModel() {

    var username by mutableStateOf("")
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()
    var isSelected by mutableIntStateOf(-1)

    var isAnswerSubmitted by mutableStateOf(false)
    var isAnswerCorrect by mutableStateOf(false)

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _correctAnsweredPoints = MutableStateFlow(0)
    val correctAnsweredPoints: StateFlow<Int> = _correctAnsweredPoints.asStateFlow()

    init {
        getQuestions()
    }

    private fun increaseScore() {
        if (isAnswerCorrect) {
            _correctAnsweredPoints.value += 1
        }
    }

    /**
     * Navigates to the next question
     * @author Ömer Aynaci
     */
    fun nextQuestion() {
        val currentQuestion = currentQuestionIndex.value
        val questions = questions.value.size
        if (currentQuestion < questions - 1) {
            _currentQuestionIndex.value += 1
            isAnswerSubmitted = false
            isSelected = -1
            if (isAnswerCorrect) {
                _correctAnsweredPoints.value += 1
            }
        }
    }


    private fun lastQuestion(onFinish: () -> Unit) {
        if (currentQuestionIndex.value == questions.value.size - 1) {
            if (isAnswerCorrect) {
                _correctAnsweredPoints.value += 1
            }
            onFinish()
        }
    }

    /**
     * Gets all the questions
     * @author Ömer Aynaci
     */
    private fun getQuestions() {
        viewModelScope.launch {
            val questionsList = animeQuizRepository.getAllQuestions()
            _questions.value = questionsList
        }
    }

    fun calculateScore(onNextClick: () -> Unit, onFinish: () -> Unit) {
        val currentQuestion = currentQuestionIndex.value
        val totalQuestions = questions.value.size
        if (currentQuestion < totalQuestions - 1) {
            onNextClick()
        } else {
            lastQuestion(onFinish)
        }
    }

    /**
     * Validates the username
     * @author Ömer Aynaci
     * @return true if the username is valid otherwise false
     */
    fun validateUsername(): Boolean {
        return username.length in 5..10
    }

    /**
     * Indicates the selected answer
     * @author Ömer Aynaci
     * @param index the index of the answers
     */
    fun selectedAnswer(index: Int) {
        isSelected = index
    }

    /**
     * Shows the answer results
     * @param index the index of the submitted answer
     * @param color the color of the results that's being highlighted
     * @return instance of the Color class
     */
    fun showSubmittedAnswerResults(index: Int, color: Color): Color {
        return if (isAnswerSubmitted) {
            if (isSelected == index) {
                if (isAnswerCorrect) Color.Green else Color.Red
            } else {
                color
            }
        } else {
            color
        }
    }

    /**
     * When an answer is selected the color is highlighted to indicate what answer the user has selected
     * @author Ömer Aynaci
     * @param isSelected checks if the user has selected an answer
     * @return an instance of the Color instance
     */
    fun borderColor(isSelected: Boolean): Color {
        return if (isSelected) {
            Color.Blue
        } else {
            Color.Gray
        }
    }

    /**
     * Submits the answer
     * @author Ömer Aunaci
     * @param correctAnswer the correct answer of the question
     */
    fun submitAnswer(correctAnswer: Int) {
        isAnswerSubmitted = true
        isAnswerCorrect = isSelected == correctAnswer
    }
}