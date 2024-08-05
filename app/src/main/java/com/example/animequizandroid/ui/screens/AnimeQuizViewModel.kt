package com.example.animequizandroid.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.animequizandroid.data.repository.AnimeQuizRepository

class AnimeQuizViewModel (
    private val animeQuizRepository: AnimeQuizRepository
) : ViewModel() {

    var username by mutableStateOf("")
}