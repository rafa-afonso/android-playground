package com.example.androidplayground.viewmodel.dateminutes

import androidx.lifecycle.ViewModel
import com.example.androidplayground.model.dateminutes.DateMinutesData
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class DateMinutesViewModel : ViewModel() {

    fun pickDate(year: Int, month: Int, dayOfMonth: Int, today: LocalDate): DateMinutesData {
        val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)

        val selectedDateString = selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        val differenceInMinutes = ChronoUnit.MINUTES.between(
            selectedDate.atStartOfDay(ZoneId.systemDefault()),
            today.atStartOfDay(ZoneId.systemDefault())
        )

        val differenceInMinutesString = differenceInMinutes.toString()

        return DateMinutesData(selectedDateString, differenceInMinutesString)
    }
}