package com.example.androidplayground

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()

        val btnSelectDate = findViewById<Button>(R.id.select_date_button)
        btnSelectDate.setOnClickListener {
            Toast.makeText(this, "Pressed", Toast.LENGTH_LONG).show()
        }
    }

    private fun setup() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mainView = findViewById<View>(R.id.main)

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