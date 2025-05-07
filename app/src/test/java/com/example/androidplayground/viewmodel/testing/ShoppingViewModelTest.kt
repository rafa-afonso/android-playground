package com.example.androidplayground.viewmodel.testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidplayground.MainCoroutineRule
import com.example.androidplayground.getOrAwaitValueTest
import com.example.androidplayground.repository.FakeShoppingRepository
import com.example.androidplayground.util.Constants
import com.example.androidplayground.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup() {
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun `insert shopping item with empty field returns error`() {
        viewModel.insertShoppingItem("name", "", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `insert shopping item with too long name returns error`() {
        val longName = buildString {
            for (i in 1..Constants.MAX_NAME_LENGTH + 1) {
                append("a")
            }
        }
        viewModel.insertShoppingItem(longName, "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `insert shopping item with too high amount returns error`() {
        viewModel.insertShoppingItem("name", "555555555555555555555555555555555555", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `insert shopping item with valid input returns success`() {
        viewModel.insertShoppingItem("name", "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()).isInstanceOf(Resource.Success::class.java)
    }
}