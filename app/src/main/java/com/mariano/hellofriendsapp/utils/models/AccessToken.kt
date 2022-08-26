package com.mariano.hellofriendsapp.utils.models

import com.mariano.hellofriendsapp.App
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
private val jsonAdapter: JsonAdapter<AccessToken> = moshi.adapter(AccessToken::class.java)

data class AccessToken(
    @Json(name = "refreshToken") val refreshToken: String,
    @Json(name = "token") val token: String
) {
    fun save() {
        val json = jsonAdapter.toJson(this)
        val sp = App.getInstance().getSharedPrefs()
        sp.edit().putString(ACCESS_TOKEN, json).apply()
    }

    fun remove() {
        val sp = App.getInstance().getSharedPrefs()
        sp.edit().remove(ACCESS_TOKEN).apply()
    }

    companion object {
        const val ACCESS_TOKEN = "accessToken"
        fun getRaw(): String? {
            val sp = App.getInstance().getSharedPrefs()
            return sp.getString(ACCESS_TOKEN, null)
        }

        fun get(): AccessToken? {
            val sp = App.getInstance().getSharedPrefs()
            val x = sp.getString(ACCESS_TOKEN, null)
            return if (x != null) {
                moshi.adapter(AccessToken::class.java).fromJson(x)
            } else {
                null
            }
        }
    }
}
