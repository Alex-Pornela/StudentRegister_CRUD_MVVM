 package com.activity.studentregistercrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.activity.studentregistercrud.DB.Student
import com.activity.studentregistercrud.DB.StudentDatabase

 class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button

    private lateinit var  viewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        saveButton = findViewById(R.id.btnSave)
        clearButton = findViewById(R.id.btnClear)

        //create data access object
        val dao = StudentDatabase.getInstance(application).studentDao()
        //create viewModel Factory passing the dao instance
        val factory = StudentViewModelFactory(dao)
        //initialize the viewModel instance using the viewModel Provider
        viewModel = ViewModelProvider(this,factory).get(StudentViewModel::class.java)

        saveButton.setOnClickListener {
            saveStudentData()
            clearInput()
        }
        clearButton.setOnClickListener {
            clearInput()
        }

    }

     private fun saveStudentData(){
        /*
        //get the inputted data
         val name = nameEditText.text.toString()
         val email = emailEditText.text.toString()
         //pass to Student Class
         val student = Student(0,name,email)
         //invoke the insert student function of the viewModel passing the student object
         viewModel.insertStudent(student)
         */
         //best way
         viewModel.insertStudent(
             Student(
                 0,
                 nameEditText.text.toString(),
                 emailEditText.text.toString()

         )
         )
     }

     private fun clearInput(){
         nameEditText.setText("")
         emailEditText.setText("")
     }
}