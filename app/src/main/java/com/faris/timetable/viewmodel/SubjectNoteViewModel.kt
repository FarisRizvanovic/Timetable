package com.faris.timetable.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.model.Note
import com.faris.timetable.model.SubjectWithDay
import kotlinx.coroutines.launch

class SubjectNoteViewModel(private val subjectId : Long, private val dao: SubjectDao) : ViewModel() {

    val notesForThisSubject = dao.getNoteForThisSubject(subjectId)

    var isDone = false

    var note = MutableLiveData<Note>(null)

    var shouldDelete = false

    //problem sa get task kad se koristi livedata u dao vidjeti zasto
    fun getTask(noteId : Long){
        viewModelScope.launch {
            note.value = dao.getNoteById(noteId)
        }
    }

    fun deleteSubject(){
        viewModelScope.launch {
            dao.deleteNote(note.value!!)
        }
    }

    fun updateTask(){
        viewModelScope.launch {
            note.value!!.noteDone = isDone
            dao.updateNote(note.value!!)
        }
    }

}