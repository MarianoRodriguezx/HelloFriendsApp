package com.mariano.hellofriendsapp.network.modules

import android.content.SharedPreferences
import com.mariano.hellofriendsapp.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideURL(): String = "http://192.168.1.173:3333/"

    @Provides
    fun provideApplication(): App = App.getInstance()

    @Provides
    fun provideSharedPreferences(app: App): SharedPreferences{
        return app.getSharedPrefs()
    }
}