package com.example.androidplayground.util

import com.example.androidplayground.BuildConfig

class Constants {
    companion object {
        const val API_KEY = BuildConfig.NEWS_API_API_KEY
        const val BASE_URL = "https://newsapi.org"
        const val SEARCH_NEWS_TIME_DELAY = 500L
        const val QUERY_PAGE_SIZE = 20
        const val DATABASE_NAME = "shopping_db"
        const val PIXABAY_BASE_URL = "https://pixabay.com"
        const val MAX_NAME_LENGTH = 20
        const val MAX_PRICE_LENGTH = 10
    }
}