package com.example.androidplayground.ui.activities.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidplayground.R
import com.example.androidplayground.databinding.ActivityNewsBinding
import com.example.androidplayground.db.news.ArticleDB
import com.example.androidplayground.repository.NewsRepository
import com.example.androidplayground.viewmodel.news.NewsViewModel
import com.example.androidplayground.viewmodel.news.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {


    lateinit var newsViewModel: NewsViewModel
    lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDB(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        newsViewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}