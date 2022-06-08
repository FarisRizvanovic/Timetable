package com.faris.timetable.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.model.Note
import com.faris.timetable.model.Subject
import com.faris.timetable.model.SubjectWithDay
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.logging.Handler

class NotesViewModel(private val dao: SubjectDao) : ViewModel() {

    private val _allSubjects = dao.getAllSubjects()
    val allSubjects : LiveData<List<Subject>>
        get() = _allSubjects

    private val _navigateToSubjectNotes = MutableLiveData<Pair<Long?, String?>>().apply { value = Pair(null, null) }
    val navigateToSubjectNotes : LiveData<Pair<Long?, String?>>
        get() = _navigateToSubjectNotes


    fun onSubjectClicked(subjectId : Long, subjectName: String){
//        _navigateToSubjectNotes.value = subjectId
        _navigateToSubjectNotes.value = Pair(subjectId,subjectName)
    }

    fun onSubjectNavigated(){
        _navigateToSubjectNotes.value = Pair(null, null)
    }
}