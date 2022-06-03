package com.faris.timetable.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.viewmodel.HomeViewModel
import com.faris.timetable.viewmodel.NotesViewModel
import java.lang.IllegalArgumentException

class HomeViewModelFactory(private val dao: SubjectDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel!")
    }
}