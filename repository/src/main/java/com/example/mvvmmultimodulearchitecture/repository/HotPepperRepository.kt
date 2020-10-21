package com.example.mvvmmultimodulearchitecture.repository

import com.example.mvvmmultimodulearchitecture.api.HotPepperApi
import com.example.mvvmmultimodulearchitecture.domain.dto.Shop
import com.example.mvvmmultimodulearchitecture.domain.repository.HotPepperRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import se.ansman.kotshi.KotshiJsonAdapterFactory


@KotshiJsonAdapterFactory
object ApplicationJsonAdapterFactory : JsonAdapter.Factory by KotshiApplicationJsonAdapterFactory

class HotPepperRepository(internal val appSettingRepository: AppSettingRepository) : HotPepperRepository {
    override suspend fun find(searchWord: String): List<Shop> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://webservice.recruit.co.jp/hotpepper/gourmet/v1/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(ApplicationJsonAdapterFactory)
                        .build()
                )
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val service = retrofit.create(HotPepperApi::class.java)
        val query = mutableMapOf(
            "key" to BuildConfig.HOT_PEPPER_API_KEY, // local.properties に Hot Pepper API のAPIキーを定義
            "format" to "json",
            "keyword" to searchWord,
            "count" to appSettingRepository.pageShopCount.toString()
        )
        val apiResult = service.find(query).await()

        return apiResult?.results?.shop?.map { resultShop ->
            val shopName = if (resultShop.name.isNullOrBlank()) "" else resultShop.name
            val address = if (resultShop.address.isNullOrBlank()) "" else resultShop.address
            val logoImage = if (resultShop.logoImage.isNullOrBlank()) "" else resultShop.logoImage
            Shop(shopName = shopName, address = address, logoImage = logoImage)
        } ?: mutableListOf()
    }
}
