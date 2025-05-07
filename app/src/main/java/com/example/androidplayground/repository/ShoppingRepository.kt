package com.example.androidplayground.repository

import androidx.lifecycle.LiveData
import com.example.androidplayground.model.testing.ImageResponse
import com.example.androidplayground.model.testing.ShoppingItem
import com.example.androidplayground.util.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}