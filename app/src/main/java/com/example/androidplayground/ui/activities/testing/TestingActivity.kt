package com.example.androidplayground.ui.activities.testing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidplayground.databinding.ActivityTestingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestingActivity : AppCompatActivity() {

    lateinit var binding: ActivityTestingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestingBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}