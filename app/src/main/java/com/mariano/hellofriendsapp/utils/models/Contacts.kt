package com.mariano.hellofriendsapp.utils.models

data class Contacts(
    val id: Int,
    val dueno: Int,
    val contacto: Int,
    val status: Int,
    val created_at: String,
    val updated_at: String,
    val UserContacto: UserData
)
