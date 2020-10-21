package com.example.mvvmmultimodulearchitecture.domain.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.mvvmmultimodulearchitecture.domain.dto.SearchHistory
import com.example.mvvmmultimodulearchitecture.domain.dto.Shop
import com.example.mvvmmultimodulearchitecture.domain.repository.HotPepperRepository
import com.example.mvvmmultimodulearchitecture.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.launch
import java.util.*

class HotPepperListViewModel(internal val hotPepperRepository: HotPepperRepository,
                             internal val searchHistoryRepository: SearchHistoryRepository): ViewModel() {

    internal val shopList: MutableList<Shop> = mutableListOf()
    private val observableShopList: MutableLiveData<List<Shop>> = MutableLiveData()
    fun observeShopList(lifecycleOwner: LifecycleOwner, observer: Observer<List<Shop>>) = observableShopList.observe(lifecycleOwner, observer)

    internal val searchHistories = mutableListOf<SearchHistory>()
    private val observableSearchHistories = MutableLiveData<List<SearchHistory>>()
    fun observeSearchHistories(lifecycleOwner: LifecycleOwner, observer: Observer<List<SearchHistory>>) = observableSearchHistories.observe(lifecycleOwner, observer)

    fun findShop(searchWord: String) = viewModelScope.launch {
        shopList.clear()
        shopList.addAll(hotPepperRepository.find(searchWord))
        observableShopList.value = shopList
    }

    fun findAllSearchHistories() = viewModelScope.launch {
        searchHistories.clear()
        searchHistories.addAll(searchHistoryRepository.getAllHistoriesOrderByCount())
        observableSearchHistories.value = searchHistories
    }

    fun saveSearchHistory(searchWord: String) = viewModelScope.launch {

        if (searchWord.isEmpty()) {
            return@launch
        }

        val searchHistory = searchHistories.firstOrNull {
            searchWord == it.searchWord
        }
        if (searchHistory == null) {
            val newHistory = SearchHistory(
                id = null,
                searchWord = searchWord,
                count = 1,
                lastSearch = Date()
            )
            newHistory.id = searchHistoryRepository.countUp(searchHistory = newHistory)
            searchHistories.add(0, newHistory)
        } else {
            searchHistory.apply {
                count++
                lastSearch = Date()
            }
            searchHistoryRepository.countUp(searchHistory = searchHistory)
            searchHistories.sortByDescending { it.lastSearch }
        }
    }
}
