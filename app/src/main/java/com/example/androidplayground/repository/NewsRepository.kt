package com.example.androidplayground.repository

import com.example.androidplayground.api.RetrofitInstance
import com.example.androidplayground.db.news.ArticleDB
import com.example.androidplayground.model.news.Article

class NewsRepository(private val articleDB: ArticleDB) {

    suspend fun getHeadlines(countryCode: String, pageNumber: Int) =
        RetrofitInstance.newsAPI.getHeadlines(countryCode = countryCode, pageNumber = pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.newsAPI.searchForNews(searchQuery = searchQuery, pageNumber = pageNumber)

    //

    suspend fun upsert(article: Article) = articleDB.getArticleDao().upsert(article)

    fun getFavoriteNews() = articleDB.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = articleDB.getArticleDao().deleteArticle(article)
}