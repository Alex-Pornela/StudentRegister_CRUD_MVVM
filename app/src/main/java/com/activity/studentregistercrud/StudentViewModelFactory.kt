package com.activity.studentregistercrud

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.activity.studentregistercrud.DB.StudentDao

/*
we can just copy this factory class and reuse it by changing the parameters
and viewModel Class name*/
class StudentViewModelFactory(
    private val dao: StudentDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StudentViewModel::class.java)){
            return StudentViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}