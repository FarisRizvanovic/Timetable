package com.faris.timetable.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faris.timetable.database.AllSubjectDao
import com.faris.timetable.model.Subject
import kotlinx.coroutines.launch

class EditViewModel(val dao: AllSubjectDao) : ViewModel() {

    val allSubjects = dao.getAllSubjects()
    var subjectName = ""


    fun addSubject() {
        viewModelScope.launch {
            val subject = Subject()
            subject.subjectName = subjectName
            dao.insertSubject(subject)
        }
    }
}