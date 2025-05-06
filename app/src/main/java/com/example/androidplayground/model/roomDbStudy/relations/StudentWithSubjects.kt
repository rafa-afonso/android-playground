package com.example.androidplayground.model.roomDbStudy.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.androidplayground.model.roomDbStudy.Student
import com.example.androidplayground.model.roomDbStudy.Subject

data class StudentWithSubjects(
    @Embedded val student: Student,

    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: List<Subject>


)
