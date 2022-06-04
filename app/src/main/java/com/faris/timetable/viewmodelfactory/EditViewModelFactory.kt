package com.faris.timetable.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.viewmodel.EditViewModel
import java.lang.IllegalArgumentException

class EditViewModelFactory(private val dao : SubjectDao)  : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditViewModel::class.java)){
            return EditViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel!")
    }

}