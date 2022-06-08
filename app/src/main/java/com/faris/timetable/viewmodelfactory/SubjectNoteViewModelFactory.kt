package com.faris.timetable.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.viewmodel.SubjectNoteViewModel
import java.lang.IllegalArgumentException

class SubjectNoteViewModelFactory(private val subjectId : Long, private val subjectDao: SubjectDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubjectNoteViewModel::class.java)){
            return SubjectNoteViewModel(subjectId, subjectDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel!")
    }
}