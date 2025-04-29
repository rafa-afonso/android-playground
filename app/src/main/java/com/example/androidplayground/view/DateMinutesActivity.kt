package com.example.androidplayground.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.androidplayground.databinding.ActivityDateMinutesBinding
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class DateMinutesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDateMinutesBinding

    private lateinit var tvSelectedDate: TextView
    private lateinit var tvSelectedDateMinutes: TextView
    private lateinit var dataView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()

        tvSelectedDate = binding.selectedDateValueStr
        tvSelectedDateMinutes = binding.minutesPassedValueStr
        dataView = binding.dateSelectedData

        val btnSelectDate = binding.selectDateButton
        btnSelectDate.setOnClickListener {
            pickDate()
        }
    }

    private fun pickDate() {

        val today = LocalDate.now()

        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)

            tvSelectedDate.text = selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

            val differenceInMinutes = ChronoUnit.MINUTES.between(
                selectedDate.atStartOfDay(ZoneId.systemDefault()),
                today.atStartOfDay(ZoneId.systemDefault())
            )

            tvSelectedDateMinutes.text = differenceInMinutes.toString()

            dataView.visibility = View.VISIBLE

        }, today.year, today.monthValue - 1, today.dayOfMonth).show()


    }

    private fun setup() {
        enableEdgeToEdge()
        binding = ActivityDateMinutesBinding.inflate(layoutInflater)
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