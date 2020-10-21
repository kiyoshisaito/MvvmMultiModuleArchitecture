package com.example.mvvmmultimodulearchitecture.domain.repository

import com.example.mvvmmultimodulearchitecture.domain.dto.SearchHistory

interface SearchHistoryRepository {
    suspend fun getAllHistoriesOrderByCount(): List<SearchHistory>
    suspend fun countUp(searchHistory: SearchHistory): Long
}