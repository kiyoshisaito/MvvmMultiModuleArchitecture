package com.example.mvvmmultimodulearchitecture.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class HotPepperApiResult(
    @Json(name = "results") val results: Results
)

@JsonSerializable
data class Results(
    @Json(name = "shop") val shop: List<Shop> = mutableListOf()
)

@JsonSerializable
data class Shop(
    val id: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "address") val address: String?,
    @Json(name = "logo_image") val logoImage: String?
)