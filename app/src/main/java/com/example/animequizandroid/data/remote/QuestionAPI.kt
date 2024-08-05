package com.example.animequizandroid.data.remote

import com.example.animequizandroid.data.dtos.Question
import retrofit2.http.GET

interface QuestionAPI {

    @GET("getQuestions")
    suspend fun getAllQuestions(): List<Question>
}