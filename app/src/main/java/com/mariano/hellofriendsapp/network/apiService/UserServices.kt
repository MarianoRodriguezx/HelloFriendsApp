package com.mariano.hellofriendsapp.network.apiService

import com.mariano.hellofriendsapp.utils.models.searchUsers
import retrofit2.http.Field
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserServices {

    @FormUrlEncoded
    @POST("searchView")
    fun searchView(
        @Field("value") value: String
    ): Call<Response<List<searchUsers>>>
}