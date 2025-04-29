package com.example.androidplayground.view.activities.dateminutes

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
import androidx.lifecycle.ViewModelProvider
import com.example.androidplayground.databinding.ActivityDateMinutesBinding
import com.example.androidplayground.viewmodel.dateminutes.DateMinutesViewModel
import java.time.LocalDate

class DateMinutesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDateMinutesBinding
    private lateinit var dateMinutesViewModel: DateMinutesViewModel

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

            val dateMinutesData = dateMinutesViewModel.pickDate(year, month, dayOfMonth, today)

            tvSelectedDate.text = dateMinutesData.selectedDate
            tvSelectedDateMinutes.text = dateMinutesData.differenceInMinutes

            dataView.visibility = View.VISIBLE

        }, today.year, today.monthValue - 1, today.dayOfMonth).show()

    }

    private fun setup() {
        enableEdgeToEdge()
        binding = ActivityDateMinutesBinding.inflate(layoutInflater)
        val mainView = binding.root
        setContentView(mainView)

        dateMinutesViewModel = ViewModelProvider(this)[DateMinutesViewModel::class.java]

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