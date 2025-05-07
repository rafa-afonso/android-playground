package com.example.androidplayground.api.news

import com.example.androidplayground.model.news.Headlines
import com.example.androidplayground.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("/v2/top-headlines")
    suspend fun getHeadlines(
        @Query("apiKey")
        apiKey: String = Constants.API_KEY,
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1
    ): Response<Headlines>

    @GET("/v2/everything")
    suspend fun searchForNews(
        @Query("apiKey")
        apiKey: String = Constants.API_KEY,
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1
    ): Response<Headlines>

}