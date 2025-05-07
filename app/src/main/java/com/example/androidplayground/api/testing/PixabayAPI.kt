package com.example.androidplayground.api.testing

import com.example.androidplayground.BuildConfig
import com.example.androidplayground.model.testing.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImages(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY
    ): Response<ImageResponse>
}