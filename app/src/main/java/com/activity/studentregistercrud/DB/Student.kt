package com.activity.studentregistercrud.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//this is a entity class
//Model

//to make this a room entity class we need annotate this to entity annotation
@Entity(tableName = "student_data_table")

data class Student(
    @PrimaryKey(autoGenerate = true)
    //to have different name in the table than the variable name just provide column annotation
    @ColumnInfo(name = "student_id")
    var id: Int,
    @ColumnInfo(name = "student_name")
    var name : String,
    @ColumnInfo(name = "student_email")
    var email : String
)
