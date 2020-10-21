package com.example.mvvmmultimodulearchitecture

import android.app.Application
import android.content.Context
import com.example.mvvmmultimodulearchitecture.di.startDI
import com.facebook.stetho.Stetho

@Suppress("ObjectPropertyName")
class App : Application() {
    companion object {
        private lateinit var _sharedInstance: Context
        val context: Context
            get() = _sharedInstance
    }
    override fun onCreate() {
        super.onCreate()

        _sharedInstance = this

        startDI(this)

        Stetho.initializeWithDefaults(this)
    }
}