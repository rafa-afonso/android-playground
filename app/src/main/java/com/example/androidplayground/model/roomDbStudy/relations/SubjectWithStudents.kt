package com.example.androidplayground.model.roomDbStudy.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.androidplayground.model.roomDbStudy.Student
import com.example.androidplayground.model.roomDbStudy.Subject

data class SubjectWithStudents(
    @Embedded val subject: Subject,

    @Relation(
        parentColumn = "subjectName",
        entityColumn = "studentName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students: List<Student>


)
