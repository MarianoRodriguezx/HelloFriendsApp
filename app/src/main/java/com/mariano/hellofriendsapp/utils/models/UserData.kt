package com.mariano.hellofriendsapp.utils.models

import androidx.annotation.Nullable

data class UserData(
    val id: Int,
    val image_profile: String,
    val username: String,
    val description: String,
    val email: String,
    val number: String,
    val rol_id: Int,
    val status: Int,
    //val remember_me_token: Any,
    val created_at: String,
    val updated_at: String
)
