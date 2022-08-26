package com.mariano.hellofriendsapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appSingleton = this
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun getSharedPrefs(): SharedPreferences {
        return getSharedPreferences("HelloFriendsAppData", Context.MODE_PRIVATE)
    }

    companion object {
        lateinit var appSingleton: App

        fun getInstance(): App {
            return appSingleton
        }
    }
}