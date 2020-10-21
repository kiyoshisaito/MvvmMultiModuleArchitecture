package com.example.mvvmmultimodulearchitecture.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.mvvmmultimodulearchitecture.domain.repository.AppSettingRepository
import com.example.mvvmmultimodulearchitecture.domain.viewmodel.AppSettingViewModel

class AppSettingRepository(context: Context): AppSettingRepository {
    private val pref: SharedPreferences = context.getSharedPreferences("appSetting", Context.MODE_PRIVATE)
    override var pageShopCount: Int
        get() = pref.getInt("pageShopCount", AppSettingViewModel.PAGE_SHOP_COUNT_10)
        set(value) = pref.edit().putInt("pageShopCount", value).apply()
}