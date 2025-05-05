package com.example.androidplayground.util

import com.example.androidplayground.BuildConfig

class Constants {
    companion object {
        const val API_KEY = BuildConfig.NEWS_API_API_KEY
        const val BASE_URL = "https://newsapi.org"
        const val SEARCH_NEWS_TIME_DELAY = 500L
        const val QUERY_PAGE_SIZE = 20
    }
}