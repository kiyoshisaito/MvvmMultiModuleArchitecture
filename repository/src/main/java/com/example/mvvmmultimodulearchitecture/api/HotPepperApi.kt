package com.example.mvvmmultimodulearchitecture.api

import com.example.mvvmmultimodulearchitecture.api.response.HotPepperApiResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface HotPepperApi {
    @GET(".")
    fun find(@QueryMap searchWord: Map<String, String>): Deferred<HotPepperApiResult?>
}