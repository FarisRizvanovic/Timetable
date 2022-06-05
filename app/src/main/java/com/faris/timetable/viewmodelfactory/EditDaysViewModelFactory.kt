package com.faris.timetable.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.viewmodel.EditDaysViewModel
import java.lang.IllegalArgumentException

class EditDaysViewModelFactory(private val dao : SubjectDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditDaysViewModel::class.java)){
            return EditDaysViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel!")
    }
}