package com.faris.timetable.viewmodel

import androidx.lifecycle.ViewModel
import com.faris.timetable.database.SubjectDao

class DayOfTheWeekSubjectsVIewModel(private val dao: SubjectDao, dayId : Int) : ViewModel() {

    val todaySubjects = dao.findTodaySubjects(dayId)

}