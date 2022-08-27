package com.mariano.hellofriendsapp.utils.models

data class TokenResponse(
    val type: String,
    val token: String,
    val expires_at: String
)
