package com.example.mvvmmultimodulearchitecture.repository

import com.example.mvvmmultimodulearchitecture.db.dao.SearchHistoryDao
import com.example.mvvmmultimodulearchitecture.domain.dto.SearchHistory
import com.example.mvvmmultimodulearchitecture.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class SearchHistoryRepository(private val searchHistoryDao: SearchHistoryDao) : SearchHistoryRepository {
    override suspend fun getAllHistoriesOrderByCount(): List<SearchHistory> = withContext(Dispatchers.IO) {
        searchHistoryDao.getAllSearchWordHistoriesOrderByCount()
            .map { dbItem: com.example.mvvmmultimodulearchitecture.db.entity.SearchHistory ->
                SearchHistory(dbItem.id, dbItem.searchWord, dbItem.count, Date(dbItem.lastSearch))
            }
    }

    override suspend fun countUp(searchHistory: SearchHistory) = withContext(Dispatchers.IO) {
        searchHistoryDao.upsert(
            searchHistory = com.example.mvvmmultimodulearchitecture.db.entity.SearchHistory(
                id = searchHistory.id,
                searchWord = searchHistory.searchWord,
                count = searchHistory.count,
                lastSearch = searchHistory.lastSearch.time
            )
        )
    }
}