package com.example.mvvmmultimodulearchitecture.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun startDI(context: Context) {
    startKoin {
        androidContext(context)
        modules(listOf(domainModule, repositoryModule))
    }
}