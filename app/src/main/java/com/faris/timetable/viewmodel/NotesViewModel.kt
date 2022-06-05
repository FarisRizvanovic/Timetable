package com.faris.timetable.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.model.Subject
import com.faris.timetable.model.SubjectWithDay
import kotlinx.coroutines.launch

class NotesViewModel(private val dao: SubjectDao) : ViewModel() {
    var newSubjectName = ""
    var newDayId = 1

    val allSubjects = dao.getAllSubjects()

    fun addSubject(){
        viewModelScope.launch {
            val subjectWithDay = SubjectWithDay()
            subjectWithDay.subjectName= newSubjectName
            subjectWithDay.dayId = newDayId
            dao.insertSubjectWithDay(subjectWithDay)
        }
    }

}