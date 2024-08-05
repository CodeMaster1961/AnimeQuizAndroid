package com.example.animequizandroid

import android.app.Application
import com.example.animequizandroid.data.di.appModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class AnimeQuizApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            modules(appModule)
        }
    }
}