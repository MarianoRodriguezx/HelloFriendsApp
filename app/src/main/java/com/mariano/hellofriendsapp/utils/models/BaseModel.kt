package com.mariano.hellofriendsapp.utils.models

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

open class BaseModel(
    open val id: Int,
    override val createdAt: String?,
    override val updatedAt: String?
) : Timestamps() {
}