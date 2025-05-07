package com.example.androidplayground.repository

import androidx.lifecycle.LiveData
import com.example.androidplayground.api.testing.PixabayAPI
import com.example.androidplayground.db.testing.ShoppingDao
import com.example.androidplayground.model.testing.ImageResponse
import com.example.androidplayground.model.testing.ShoppingItem
import com.example.androidplayground.util.Resource
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImages(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknown error has occurred", null)
            } else {
                Resource.Error("An unknown error has occurred", null)
            }
        } catch (e: Exception) {
            Resource.Error("Couldn't reach the server. Check yout internet connection", null)
        }
    }
}