package com.example.mvvmmultimodulearchitecture.di

import com.example.mvvmmultimodulearchitecture.domain.viewmodel.AppSettingViewModel
import com.example.mvvmmultimodulearchitecture.domain.viewmodel.HotPepperListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domainModule = module {
    viewModel { AppSettingViewModel(get()) }
    viewModel { HotPepperListViewModel(get(), get()) }
}