package com.example.mvvmmultimodulearchitecture.di

import android.content.Context
import com.example.mvvmmultimodulearchitecture.db.AppDatabase
import com.example.mvvmmultimodulearchitecture.db.dao.SearchHistoryDao
import com.example.mvvmmultimodulearchitecture.domain.repository.SearchHistoryRepository
import com.example.mvvmmultimodulearchitecture.repository.AppSettingRepository
import com.example.mvvmmultimodulearchitecture.repository.HotPepperRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { createAppSettingRepository(get()) }
    single { createHotPepperRepository(AppSettingRepository(get())) }
    single { createAppDatabase(get()) }
    single { createSearchHistoryDao(get()) }
    single { createSearchHistoryRepository(get()) }
}

private fun createHotPepperRepository(appSettingRepository: AppSettingRepository): com.example.mvvmmultimodulearchitecture.domain.repository.HotPepperRepository {
    return HotPepperRepository(appSettingRepository)
}
private fun createAppSettingRepository(context: Context): com.example.mvvmmultimodulearchitecture.domain.repository.AppSettingRepository {
    return AppSettingRepository(context)
}
private fun createAppDatabase(context: Context): AppDatabase {
    return AppDatabase.createRoomDatabase(context)
}
private fun createSearchHistoryDao(appDatabase: AppDatabase): SearchHistoryDao {
    return appDatabase.searchHistoryDao()
}
private fun createSearchHistoryRepository(searchHistoryDao: SearchHistoryDao): SearchHistoryRepository {
    return com.example.mvvmmultimodulearchitecture.repository.SearchHistoryRepository(searchHistoryDao = searchHistoryDao)
}