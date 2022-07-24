package com.activity.studentregistercrud.DB

import androidx.lifecycle.LiveData
import androidx.room.*

//data access object where we define function to deal with database
@Dao
interface StudentDao {
    //to tell the room library that this function will save an object annotate with @Insert keyword
    @Insert
    //function to insert student data
    suspend fun insertStudent(student: Student)

    //to update student data
    @Update
    suspend fun updateStudent(student: Student)

    //to delete student data
    @Delete
    suspend fun deleteStudent(student: Student)

    //write sql query
    @Query("SELECT * FROM student_data_table")
    //define query function to get yhe list of save student data from the database as live data
    fun getAllStudent():LiveData<List<Student>>
}