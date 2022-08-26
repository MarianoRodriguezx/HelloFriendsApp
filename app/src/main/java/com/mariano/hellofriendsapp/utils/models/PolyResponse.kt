package com.mariano.hellofriendsapp.utils.models

import com.squareup.moshi.JsonClass
import dev.onenowy.moshipolymorphicadapter.PolymorphicAdapterType
import dev.onenowy.moshipolymorphicadapter.annotations.ValueLabel

@JsonClass(generateAdapter = true, generator = "${PolymorphicAdapterType.VALUE_POLYMORPHIC_ADAPTER_BOOLEAN}:status")
sealed class PolyResponse

@ValueLabel(true.toString())
data class SuccessResponse (val status: Boolean, val data: Any, val msg: String): PolyResponse()

@ValueLabel(false.toString())
data class FailureResponse(val status: Boolean, val data: String?, val msg: String): PolyResponse()