 package com.activity.studentregistercrud

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.activity.studentregistercrud.DB.Student
import com.activity.studentregistercrud.DB.StudentDatabase
import com.activity.studentregistercrud.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {
     //viewBinding
     private lateinit var binding: ActivityMainBinding

    private lateinit var  viewModel: StudentViewModel
    // define reference variable for the recycleView and its adapter
    private lateinit var studentRecycleViewAdapter: RecyclerView
    private lateinit var adapter: StudentRecycleViewAdapter
    //hold selected Student
    private lateinit var selectedStudent : Student
    //boolean to check if list_item click
    private var isListItemClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        studentRecycleViewAdapter = findViewById(R.id.rvStudent)

        //create data access object
        val dao = StudentDatabase.getInstance(application).studentDao()
        //create viewModel Factory passing the dao instance
        val factory = StudentViewModelFactory(dao)
        //initialize the viewModel instance using the viewModel Provider
        viewModel = ViewModelProvider(this,factory).get(StudentViewModel::class.java)


        binding.btnSave.setOnClickListener {
            if(isListItemClicked){
                updateStudentData()
                clearInput()
            }else{
                saveStudentData()
                clearInput()
            }

        }
        binding.btnClear.setOnClickListener {
            if(isListItemClicked){
                deleteStudenData()
                clearInput()
            }else{
                clearInput()
            }

        }
        //call the function
        initRecyleView()

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
         binding.apply {
             viewModel.insertStudent(
                 Student(
                     0,
                     etName.text.toString(),
                     etEmail.text.toString()

                 )
             )
         }

     }

     private fun updateStudentData(){
         binding.apply {
             viewModel.updateStudent(
                 //create and pass updated student object using the primary key which is id
                 Student(
                     selectedStudent.id,
                     etName.text.toString(),
                     etEmail.text.toString()

                 )
             )
             //after updating we need to take the screen back to default state
             btnSave.text = "Save"
             btnClear.text = "Clear"
             //set to boolean isListItemClicked to true
             isListItemClicked = false
         }

     }

     private fun deleteStudenData(){
         binding.apply {
             viewModel.deleteStudent(
                 Student(
                     selectedStudent.id,
                     etName.text.toString(),
                     etEmail.text.toString()
                 )
             )
             //after updating we need to take the screen back to default state
             btnSave.text = "Save"
             btnClear.text = "Clear"
             //set to boolean isListItemClicked to true
             isListItemClicked = false
         }

     }

     private fun clearInput(){
         binding.apply {
             etName.setText("")
            etEmail.setText("")
         }

     }

     //displaying the list through recycleView
     fun initRecyleView(){
         //set layoutManger as Linear layoutManager
         studentRecycleViewAdapter.layoutManager = LinearLayoutManager(this)
         //initialize the adapter
         adapter = StudentRecycleViewAdapter{
             selectedItem:Student -> listItemClicked(selectedItem)
         }
         //set the adapter in recycleView
         studentRecycleViewAdapter.adapter = adapter

         //display the studentList in recycleView
         displayStudentList()
     }

     //observe the list from the student ViewModel
     @SuppressLint("NotifyDataSetChanged")
     private fun displayStudentList(){
         //"it" represent the list of student objects provided as livedata
         viewModel.students.observe(this,{
             //pass the list to the adapter using its set list function
             adapter.setList(it)
             //invoke the notify dataset changed function of the adpater
             adapter.notifyDataSetChanged()
         })
     }

     //function for click event in list_item
     private fun listItemClicked(student: Student){
         binding.apply {
             /*Toast.makeText(this,"Student name is ${student.name}",
        Toast.LENGTH_LONG
        ).show()*/
             //assign selected student object to it and change button text to Update and Delete
             selectedStudent = student
             btnSave.text = "Update"
             btnClear.text = "Delete"
             //set to boolean isListItemClicked to true
             isListItemClicked = true
             //display selected students name and email on the text input fields
             etName.setText(selectedStudent.name)
             etEmail.setText(selectedStudent.email)
         }

     }
}