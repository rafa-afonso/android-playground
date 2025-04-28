package com.example.androidplayground

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvSelectedDateMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()

        val btnSelectDate = findViewById<Button>(R.id.select_date_button)
        tvSelectedDate = findViewById(R.id.selected_date_value_str)
        tvSelectedDateMinutes = findViewById(R.id.minutes_passed_value_str)
        btnSelectDate.setOnClickListener {
            pickDate()
        }
    }

    private fun pickDate() {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDateString = "$selectedDay/${selectedMonth + 1}/$selectedYear"

            tvSelectedDate?.text = selectedDateString

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
            val selectedDate = simpleDateFormat.parse(selectedDateString)

            val selectedDateInMs = selectedDate?.time ?: 0
            val currentDateInMs =
                simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))?.time
                    ?: 0


            val differenceInMs = currentDateInMs - selectedDateInMs

            val differenceInMinutes = differenceInMs / (1000 * 60)

            tvSelectedDateMinutes?.text = differenceInMinutes.toString()


        }, year, month, day).show()


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