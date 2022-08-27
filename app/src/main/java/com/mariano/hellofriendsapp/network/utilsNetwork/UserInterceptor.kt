package com.mariano.hellofriendsapp.network.utilsNetwork

import android.util.Log
import com.mariano.hellofriendsapp.App
import com.mariano.hellofriendsapp.utils.models.AccessToken
import okhttp3.Interceptor
import okhttp3.Response

class UserInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val app = App.getInstance()
        val token = app.getSharedPrefs().getString("ACCESS_TOKEN", null)

        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Accept", "application/json")
        Log.d("Token", "$token")

        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}