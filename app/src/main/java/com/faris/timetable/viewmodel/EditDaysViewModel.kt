package com.faris.timetable.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.model.Subject
import com.faris.timetable.model.SubjectWithDay
import kotlinx.coroutines.launch

class EditDaysViewModel(private val dao: SubjectDao) : ViewModel() {

    val allSubjects = dao.getAllSubjects()
    val allOptionsList = MutableLiveData<List<String>>()

    val mondaySubjects = dao.findTodaySubjects(1)
    val tuesdaySubjects = dao.findTodaySubjects(2)
    val wednesdaySubjects = dao.findTodaySubjects(3)
    val thursdaySubjects = dao.findTodaySubjects(4)
    val fridaySubjects = dao.findTodaySubjects(5)


    fun getAllOptions() {
        viewModelScope.launch {
            val listOfOptions = mutableListOf<String>("Predmet")
            for (index in 0 until allSubjects.value!!.size){
                val subjectName = allSubjects.value!![index].subjectName
                listOfOptions.add(subjectName)
            }
            allOptionsList.value = listOfOptions
        }
    }

    fun addSubjectToDay(subjectName : String, dayId : Int){
        viewModelScope.launch {
            val subjectWithDay = SubjectWithDay()
            subjectWithDay.subjectName = subjectName
            subjectWithDay.dayId = dayId
            dao.insertSubjectWithDay(subjectWithDay)
        }
    }

    fun deleteSubject(subjectId : Long){
        viewModelScope.launch {
            val subject = SubjectWithDay(subjectId = subjectId)
            dao.deleteSubjectWithDay(subject)
        }
    }

}