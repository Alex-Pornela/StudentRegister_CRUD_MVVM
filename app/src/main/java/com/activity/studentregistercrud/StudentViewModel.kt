package com.activity.studentregistercrud

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.activity.studentregistercrud.DB.Student
import com.activity.studentregistercrud.DB.StudentDao
import kotlinx.coroutines.launch

class StudentViewModel(private val dao: StudentDao): ViewModel() {

    val students = dao.getAllStudent()

    //to received the list of saved student instances, just observe the live data from the main activity
    //define function to save student data to the database using Dao's student insert function
    fun insertStudent(student: Student) = viewModelScope.launch {
        dao.insertStudent(student)
    }
    fun updateStudent(student: Student) = viewModelScope.launch {
        dao.updateStudent(student)
    }
    fun deleteStudent(student: Student) = viewModelScope.launch {
        dao.deleteStudent(student)
    }
}