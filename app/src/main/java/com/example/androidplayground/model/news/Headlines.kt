package com.example.androidplayground.model.news

data class Headlines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)