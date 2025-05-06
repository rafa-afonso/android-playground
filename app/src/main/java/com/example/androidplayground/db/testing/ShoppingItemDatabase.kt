package com.example.androidplayground.db.testing

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidplayground.model.testing.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingItemDatabase : RoomDatabase() {
    abstract fun shoppingDao(): ShoppingDao
}