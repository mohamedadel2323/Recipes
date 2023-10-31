package com.example.recipes

import android.app.Application
import com.example.recipes.di.appModule
import com.example.recipes.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, viewModels)
        }
    }
}