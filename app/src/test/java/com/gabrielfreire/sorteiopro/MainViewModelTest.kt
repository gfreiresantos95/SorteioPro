package com.gabrielfreire.sorteiopro

import com.gabrielfreire.sorteiopro.viewmodels.MainViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(dispatcher = StandardTestDispatcher())

    private val testDispatcher = mainCoroutineRule.dispatcher

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun `estado inicial deve ser isLoading=true e keepSplashOn=true`() =
        runTest(context = testDispatcher) {
            assertThat(viewModel.isLoading.value).isTrue()
            assertThat(viewModel.keepSplashOn.value).isTrue()
        }

    @Test
    fun `estados devem permanecer true apos 500ms (antes do delay)`() =
        runTest(context = testDispatcher) {
            advanceTimeBy(delayTimeMillis = 500L)

            assertThat(viewModel.isLoading.value).isTrue()
            assertThat(viewModel.keepSplashOn.value).isTrue()
        }

    @Test
    fun `estados devem mudar para false apos 1500ms (apos o delay)`() =
        runTest(context = testDispatcher) {
            advanceTimeBy(delayTimeMillis = 1500L)

            assertThat(viewModel.isLoading.value).isFalse()
            assertThat(viewModel.keepSplashOn.value).isFalse()
        }
}