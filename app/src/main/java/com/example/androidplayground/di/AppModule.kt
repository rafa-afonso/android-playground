package com.example.androidplayground.di

import android.content.Context
import androidx.room.Room
import com.example.androidplayground.api.testing.PixabayAPI
import com.example.androidplayground.db.testing.ShoppingItemDatabase
import com.example.androidplayground.util.Constants.Companion.DATABASE_NAME
import com.example.androidplayground.util.Constants.Companion.PIXABAY_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PIXABAY_BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }

}