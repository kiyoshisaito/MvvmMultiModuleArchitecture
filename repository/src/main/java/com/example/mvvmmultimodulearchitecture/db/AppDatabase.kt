package com.example.mvvmmultimodulearchitecture.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmmultimodulearchitecture.db.dao.SearchHistoryDao
import com.example.mvvmmultimodulearchitecture.db.entity.SearchHistory

@Database(entities = [
    SearchHistory::class
], version = 1)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        fun createRoomDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "sample").build()
        }
    }
    abstract fun searchHistoryDao(): SearchHistoryDao
}