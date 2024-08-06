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

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    init {
        getQuestions()
    }

    private fun getQuestions() {
        viewModelScope.launch {
            val questionsList = animeQuizRepository.getAllQuestions()
            _questions.value = questionsList
        }
    }

    fun validateUsername(): Boolean {
        return username.length in 5..10
    }

    fun selectedAnswer(index: Int) {
        isSelected = index
    }

    fun borderColor(isSelected: Boolean): Color {
        return if (isSelected) {
            Color.Blue
        } else {
            Color.Gray
        }
    }

    fun showCorrectAnswer(question: Question, index: Int) {
        if (index == question.correctAnswer) {
            println("You are correct!")
        } else {
            println("You are wrong!")
        }
    }
}