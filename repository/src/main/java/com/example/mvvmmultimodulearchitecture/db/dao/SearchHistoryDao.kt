package com.example.mvvmmultimodulearchitecture.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmmultimodulearchitecture.db.entity.SearchHistory

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM searchHistory ORDER BY last_search DESC, count ASC")
    fun getAllSearchWordHistoriesOrderByCount(): List<SearchHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(searchHistory: SearchHistory): Long
}