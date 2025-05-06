package com.example.androidplayground.db.roomDbStudy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidplayground.model.roomDbStudy.Director
import com.example.androidplayground.model.roomDbStudy.School
import com.example.androidplayground.model.roomDbStudy.Student
import com.example.androidplayground.model.roomDbStudy.Subject
import com.example.androidplayground.model.roomDbStudy.relations.StudentSubjectCrossRef

@Database(
    entities = [
        School::class,
        Student::class,
        Director::class,
        Subject::class,
        StudentSubjectCrossRef::class
    ],
    version = 1
)
abstract class SchoolDB : RoomDatabase() {

    abstract val schoolDao: SchoolDao

    companion object {
        @Volatile
        private var INSTANCE: SchoolDB? = null

        fun getInstance(context: Context): SchoolDB {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDB::class.java,
                    name = "school_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}