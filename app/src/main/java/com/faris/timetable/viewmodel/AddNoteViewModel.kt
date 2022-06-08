package com.faris.timetable.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.model.Note
import kotlinx.coroutines.launch

class AddNoteViewModel(private val subjectId: Long, private val dao: SubjectDao) : ViewModel() {
    var noteBody = ""

    fun insertNoteForThisSubject(){
        viewModelScope.launch {
            val note = Note()
            note.subjectId= subjectId
            note.noteBody = noteBody
            dao.insertNote(note)
        }
    }
}