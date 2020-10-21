package com.example.mvvmmultimodulearchitecture.repository

import com.example.testlib.TestExtension
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@Suppress("NonAsciiCharacters", "TestFunctionName")
@ExperimentalCoroutinesApi
@ExtendWith(TestExtension::class, MockKExtension::class)
class HotPepperRepositoryTest {
    @Test
    fun `検索条件は最寄り駅にHotPepperAPIを叩き、検索結果を取得する。`() {
        // arrange
        val hotPeeperRepository = HotPepperRepository(appSettingRepository = mockk(relaxed = true))
        val searchWord = "中央区"
        every { hotPeeperRepository.appSettingRepository.pageShopCount } returns 10
        // act
        val result = runBlocking { hotPeeperRepository.find(searchWord) }
        // assert
        assertTrue(result.isNotEmpty())
    }

}