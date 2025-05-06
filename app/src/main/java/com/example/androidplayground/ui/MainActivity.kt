package com.example.androidplayground.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.androidplayground.databinding.ActivityMainBinding
import com.example.androidplayground.ui.activities.dateminutes.DateMinutesActivity
import com.example.androidplayground.ui.activities.mvvmcalculator.MVVMCalculatorActivity
import com.example.androidplayground.ui.activities.news.NewsActivity
import com.example.androidplayground.ui.activities.roomDbStudy.RoomDbStudyActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()

        val btnMVVMCalculator = binding.btnMVVMCalculator
        btnMVVMCalculator.setOnClickListener {
            val intent = Intent(this, MVVMCalculatorActivity::class.java)
            startActivity(intent)
        }

        val btnDateInMinutes = binding.btnDateMinutes
        btnDateInMinutes.setOnClickListener {
            val intent = Intent(this, DateMinutesActivity::class.java)
            startActivity(intent)
        }

        val btnNewsApp = binding.btnNewsApp
        btnNewsApp.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }

        val btnRoomDbStudy = binding.btnRoomDbStudy
        btnRoomDbStudy.setOnClickListener {
            val intent = Intent(this, RoomDbStudyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setup() {
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val mainView = binding.root
        setContentView(mainView)

        val originalPaddingLeft = mainView.paddingLeft
        val originalPaddingTop = mainView.paddingTop
        val originalPaddingRight = mainView.paddingRight
        val originalPaddingBottom = mainView.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.updatePadding(
                left = originalPaddingLeft + systemBars.left,
                top = originalPaddingTop + systemBars.top,
                right = originalPaddingRight + systemBars.right,
                bottom = originalPaddingBottom + systemBars.bottom
            )
            insets
        }
    }
}