package com.example.mvvmmultimodulearchitecture.domain.viewmodel

import com.example.mvvmmultimodulearchitecture.domain.dto.SearchHistory
import com.example.mvvmmultimodulearchitecture.domain.dto.Shop
import com.example.mvvmmultimodulearchitecture.domain.extension.add
import com.example.testlib.TestExtension
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@Suppress("NonAsciiCharacters", "TestFunctionName", "ClassName")
@ExperimentalCoroutinesApi
@ExtendWith(TestExtension::class, MockKExtension::class)
class HotPepperListViewModelTest {

    private val viewModel = HotPepperListViewModel(hotPepperRepository = mockk(relaxed = true),
        searchHistoryRepository = mockk(relaxed = true))

    @Test
    fun `検索条件文言を条件にHotPepperAPIでお店を検索し、結果のリストを返却する`() {
        // arrange
        val searchWord = "新宿"
        coEvery { viewModel.hotPepperRepository.find(searchWord) } returns mutableListOf(
            Shop(shopName = "test name", address = "address$searchWord", logoImage = "logoImageUrl")
        )

        // act
        viewModel.findShop(searchWord)

        // assert
        assertTrue(viewModel.shopList.isNotEmpty())
        coVerify { viewModel.hotPepperRepository.find(searchWord) }
        viewModel.shopList.forEach {
            assertTrue(it.address.contains(searchWord))
        }
    }

    @Nested
    inner class 検索した文字列の保存 {
        private fun actAndAssert(searchWord: String, newHistory: SearchHistory) {

            // act
            viewModel.saveSearchHistory(searchWord)

            // assert
            coVerify(exactly = 1) { viewModel.searchHistoryRepository.countUp(searchHistory = SearchHistory(
                id = newHistory.id,
                searchWord = newHistory.searchWord,
                count = newHistory.count,
                lastSearch = newHistory.lastSearch
            )) }

            assertTrue(viewModel.searchHistories[0] == newHistory)
        }
        @Test fun `検索したこと無い文字列の場合 -＞ id=null , 検索回数=1 , 日付=今日日付 で保存実行し、検索履歴リストの０番目になる`() {
            // arrange
            val searchWord = "新宿"
            val newHistory = SearchHistory(id = 1, searchWord = "test", count = 1, lastSearch = Date())
            viewModel.searchHistories.apply { add(newHistory) }

            actAndAssert(searchWord = searchWord, newHistory = SearchHistory(id = null, searchWord = searchWord, count = 1, lastSearch = Date()))
        }
        @Test fun `検索したことある文字列の場合 -＞ 検索履歴で一致する文字列のオブジェクトを id=nullでない , 検索回数=プラス１ , 日付=今日日付 で保存実行し、検索履歴リストの０番目になる`() {
            // arrange
            val searchWord = "博多"
            viewModel.searchHistories.apply {
                add(SearchHistory(id = 1, searchWord = "test", count = 1, lastSearch = Date().add(day = -1)))
                add(SearchHistory(id = 2, searchWord = searchWord, count = 2, lastSearch = Date()))
            }

            actAndAssert(searchWord = searchWord, newHistory = SearchHistory(id = 2, searchWord = searchWord, count = 3, lastSearch = Date()))
        }
    }
}