package com.example.androidplayground.model.roomDbStudy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class School(

    @PrimaryKey(autoGenerate = false)
    val schoolName: String

)