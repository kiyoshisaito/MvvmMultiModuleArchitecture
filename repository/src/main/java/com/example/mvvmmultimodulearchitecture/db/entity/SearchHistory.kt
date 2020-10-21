package com.example.mvvmmultimodulearchitecture.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchHistory(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "search_word") val searchWord: String,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "last_search") val lastSearch: Long
)