package com.example.recipes

import android.app.Application

class MyApplication : Application(){
    val dependenciesProvider = DependenciesProvider()
}