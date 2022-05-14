package com.example.test.application

import android.app.Application
import com.example.test.di.mainModule
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class AltemisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                mainModule
            )
        }
    }
}