package com.example.mvvmmultimodulearchitecture.domain.dto

import java.io.Serializable

data class Shop(
    var shopName: String,
    var address: String,
    var logoImage: String
): Serializable
