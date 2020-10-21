package com.example.testlib

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import io.mockk.clearAllMocks
import io.mockk.clearStaticMockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
/**
 * For Junit5 testing class.
 * override before, beforeEach, after, afterEach and etc.
 *
 * coroutine testing on junit5
 * https://medium.com/@roman.tikhonov/unit-testing-kotlin-coroutines-on-android-with-junit-5-31b2c3c41157
 * liveData testing on junit5
 * https://jeroenmols.com/blog/2019/01/17/livedatajunit5/
 */
@ExtendWith
@ExperimentalCoroutinesApi
class TestExtension(
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : BeforeEachCallback, AfterEachCallback, TestCoroutineScope by TestCoroutineScope(dispatcher) {
    @SuppressLint("RestrictedApi")
    override fun beforeEach(context: ExtensionContext?) {
        clearAllMocks()
        clearStaticMockk()
        // for coroutine testing
        Dispatchers.setMain(dispatcher)
        // for liveData testing
        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()
                override fun postToMainThread(runnable: Runnable) = runnable.run()
                override fun isMainThread(): Boolean {
                    return true
                }
            })
    }
    @SuppressLint("RestrictedApi")
    override fun afterEach(context: ExtensionContext?) {
        // for coroutine testing
        cleanupTestCoroutines()
        Dispatchers.resetMain()
        // for liveData testing
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}