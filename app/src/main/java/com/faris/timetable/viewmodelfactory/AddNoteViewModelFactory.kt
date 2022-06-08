package com.faris.timetable.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.viewmodel.AddNoteViewModel
import java.lang.IllegalArgumentException

class AddNoteViewModelFactory(private val subjectId: Long, private val dao: SubjectDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java)){
            return AddNoteViewModel(subjectId, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel!")
    }

}