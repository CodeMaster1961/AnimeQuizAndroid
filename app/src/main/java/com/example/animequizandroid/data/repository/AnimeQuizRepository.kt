package com.example.animequizandroid.data.repository

import com.example.animequizandroid.data.dtos.Question

interface AnimeQuizRepository {
    suspend fun getAllQuestions(): List<Question>
}