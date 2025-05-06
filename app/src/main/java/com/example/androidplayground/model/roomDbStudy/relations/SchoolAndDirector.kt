package com.example.androidplayground.model.roomDbStudy.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.androidplayground.model.roomDbStudy.Director
import com.example.androidplayground.model.roomDbStudy.School

data class SchoolAndDirector( // School written first = parent in relation
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName", // column name in the parent entity
        entityColumn = "schoolName" // column name in the child entity
    )
    val director: Director
)