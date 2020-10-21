package com.example.mvvmmultimodulearchitecture.domain.repository

import com.example.mvvmmultimodulearchitecture.domain.dto.Shop

interface HotPepperRepository {
    suspend fun find(searchWord: String): List<Shop>
}