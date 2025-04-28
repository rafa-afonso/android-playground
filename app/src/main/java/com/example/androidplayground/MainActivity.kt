package com.example.androidplayground

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvSelectedDateMinutes: TextView? = null
    private var dataView: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()

        val btnSelectDate = findViewById<Button>(R.id.select_date_button)
        tvSelectedDate = findViewById(R.id.selected_date_value_str)
        tvSelectedDateMinutes = findViewById(R.id.minutes_passed_value_str)
        dataView = findViewById(R.id.date_selected_data)
        btnSelectDate.setOnClickListener {
            pickDate()
        }
    }

    private fun pickDate() {

        val today = LocalDate.now()

        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)

            tvSelectedDate?.text = selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

            val differenceInMinutes = ChronoUnit.MINUTES.between(
                selectedDate.atStartOfDay(ZoneId.systemDefault()),
                today.atStartOfDay(ZoneId.systemDefault())
            )

            tvSelectedDateMinutes?.text = differenceInMinutes.toString()

            dataView?.visibility = View.VISIBLE

        }, today.year, today.monthValue - 1, today.dayOfMonth).show()


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