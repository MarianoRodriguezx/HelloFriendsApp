package com.mariano.hellofriendsapp.network.apiService

import com.mariano.hellofriendsapp.utils.models.DataProfile
import com.mariano.hellofriendsapp.utils.models.TokenClosed
import com.mariano.hellofriendsapp.utils.models.TokenResponse
import com.mariano.hellofriendsapp.utils.models.searchUsers
import retrofit2.http.Field
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserServices {

    @FormUrlEncoded
    @POST("searchView")
    fun searchView(
        @Field("value") value: String
    ): Call<Response<List<searchUsers>>>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Response<TokenResponse>>

    @GET("logout")
    fun logout(): Call<Response<TokenClosed>>

    @GET("userInfo")
    fun userInfo(): Call<Response<DataProfile>>

}