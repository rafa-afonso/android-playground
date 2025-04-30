package com.example.androidplayground.model.news

data class Headlines(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)