package com.example.androidplayground.model.roomDbStudy.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.androidplayground.model.roomDbStudy.School
import com.example.androidplayground.model.roomDbStudy.Student

data class SchoolWithStudents(
    @Embedded val school: School,

    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val students: List<Student>


)
