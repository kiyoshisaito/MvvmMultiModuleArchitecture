package com.example.mvvmmultimodulearchitecture.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmmultimodulearchitecture.domain.repository.AppSettingRepository

class AppSettingViewModel(private val appSettingRepository: AppSettingRepository): ViewModel() {
    companion object {
        const val PAGE_SHOP_COUNT_10 = 10
        private const val PAGE_SHOP_COUNT_20 = 20
    }
    private var pageShopCount: Int
        get() = appSettingRepository.pageShopCount
        set(value) { appSettingRepository.pageShopCount = value }
    fun savePageShopCount10() {
        pageShopCount = PAGE_SHOP_COUNT_10
    }
    fun savePageShopCount20() {
        pageShopCount = PAGE_SHOP_COUNT_20
    }
}