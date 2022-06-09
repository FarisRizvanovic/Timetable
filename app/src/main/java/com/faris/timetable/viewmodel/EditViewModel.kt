package com.faris.timetable.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.model.Subject
import kotlinx.coroutines.launch

class EditViewModel(val dao: SubjectDao) : ViewModel() {

    val allSubjects = dao.getAllSubjects()
    var subjectName = ""

    fun addSubject() {
        viewModelScope.launch {
            val subject = Subject()
            subject.subjectName = subjectName
            dao.insertSubject(subject)
        }
    }

    fun deleteSubject(subjectId : Long){
        viewModelScope.launch {
            val subject = Subject(subjectId = subjectId)
            dao.deleteSubject(subject)
            dao.deleteNotesAfterSubject(subjectId)
        }
    }

}