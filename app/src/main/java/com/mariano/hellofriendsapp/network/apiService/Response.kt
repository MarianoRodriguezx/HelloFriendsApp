package com.mariano.hellofriendsapp.network.apiService

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException

class Response<A> (val msg: String, val status: Boolean, val data: A)

class EmptyResponse(val msg: String, val status: Boolean)

class NullResponse()

private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

//@JsonClass(generateAdapter = true)
class ErrorResponse(val msg: String = "",
                    val status: Boolean = false) {
    companion object {
        val defErrorResponse = ErrorResponse("Error del servidor", false)
        fun parse(str: String?): ErrorResponse {
            if (str == null) {
                return ErrorResponse("Error del servidor", false)
            }
            return try {
                moshi.adapter(ErrorResponse::class.java).fromJson(str) ?: defErrorResponse
            } catch (e: IOException) {
                ErrorResponse("Error del servidor", false)
            }
        }
    }
}