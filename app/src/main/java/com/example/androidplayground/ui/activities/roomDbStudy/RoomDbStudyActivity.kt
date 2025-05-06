package com.example.androidplayground.ui.activities.roomDbStudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidplayground.databinding.ActivityRoomDbStudyBinding
import com.example.androidplayground.db.roomDbStudy.SchoolDB
import com.example.androidplayground.model.roomDbStudy.Director
import com.example.androidplayground.model.roomDbStudy.School
import com.example.androidplayground.model.roomDbStudy.Student
import com.example.androidplayground.model.roomDbStudy.Subject
import com.example.androidplayground.model.roomDbStudy.relations.StudentSubjectCrossRef
import kotlinx.coroutines.launch

class RoomDbStudyActivity : AppCompatActivity() {

    lateinit var binding: ActivityRoomDbStudyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomDbStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = SchoolDB.getInstance(this).schoolDao

        val directors = listOf(
            Director("Dir 1", "Rafa School"),
            Director("Dir 2", "Kotlin School"),
            Director("Dir 3", "JetBrains School")
        )
        val schools = listOf(
            School("Rafa School"),
            School("Kotlin School"),
            School("JetBrains School")
        )
        val subjects = listOf(
            Subject("Dating for programmers"),
            Subject("Avoiding depression"),
            Subject("Bug Fix Meditation"),
            Subject("Logcat for Newbies"),
            Subject("How to use Google")
        )
        val students = listOf(
            Student("Student 1", 2, "Kotlin School"),
            Student("Student 2", 5, "Rafa School"),
            Student("Student 3", 8, "Kotlin School"),
            Student("Student 4", 1, "Kotlin School"),
            Student("Student 5", 2, "JetBrains School")
        )
        val studentSubjectRelations = listOf(
            StudentSubjectCrossRef("Student 1", "Dating for programmers"),
            StudentSubjectCrossRef("Student 1", "Avoiding depression"),
            StudentSubjectCrossRef("Student 1", "Bug Fix Meditation"),
            StudentSubjectCrossRef("Student 1", "Logcat for Newbies"),
            StudentSubjectCrossRef("Student 2", "Dating for programmers"),
            StudentSubjectCrossRef("Student 3", "How to use Google"),
            StudentSubjectCrossRef("Student 4", "Logcat for Newbies"),
            StudentSubjectCrossRef("Student 5", "Avoiding depression"),
            StudentSubjectCrossRef("Student 5", "Dating for programmers")
        )
        lifecycleScope.launch {
            directors.forEach { dao.insertDirector(it) }
            schools.forEach { dao.insertSchool(it) }
            subjects.forEach { dao.insertSubject(it) }
            students.forEach { dao.insertStudent(it) }
            studentSubjectRelations.forEach { dao.insertStudentSubjectCrossRef(it) }

            val schoolWithDirector = dao.getSchoolAndDirectorWithSchoolName("Kotlin School")

            val schoolWithStudents = dao.getSchoolWithStudentsWithSchoolName("Kotlin School")
        }
    }
}