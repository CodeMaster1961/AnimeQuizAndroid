package com.example.animequizandroid.data.repository

import com.example.animequizandroid.data.dtos.Question
import com.example.animequizandroid.data.remote.QuestionAPI

class AnimeQuizImplementation (
    private val api: QuestionAPI
): AnimeQuizRepository {

    override suspend fun getAllQuestions(): List<Question> {
        return api.getAllQuestions()
    }
}