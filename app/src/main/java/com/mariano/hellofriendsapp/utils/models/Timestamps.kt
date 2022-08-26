package com.mariano.hellofriendsapp.utils.models

import com.squareup.moshi.Json

abstract class Timestamps {
    @Json(name = "created_at") abstract val createdAt: String?
    @Json(name = "updated_at") abstract val updatedAt: String?
}