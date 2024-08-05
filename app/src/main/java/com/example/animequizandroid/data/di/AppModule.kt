package com.example.animequizandroid.data.di

import com.example.animequizandroid.data.remote.QuestionAPI
import com.example.animequizandroid.data.remote.request.retrofitInstance
import com.example.animequizandroid.data.repository.AnimeQuizImplementation
import com.example.animequizandroid.data.repository.AnimeQuizRepository
import com.example.animequizandroid.ui.screens.AnimeQuizViewModel
import org.koin.dsl.module

val appModule = module {
    single {
        AnimeQuizViewModel(get())
    }

    single<AnimeQuizRepository> {
        AnimeQuizImplementation(get())
    }

    single {
        retrofitInstance().create(QuestionAPI::class.java)
    }
}