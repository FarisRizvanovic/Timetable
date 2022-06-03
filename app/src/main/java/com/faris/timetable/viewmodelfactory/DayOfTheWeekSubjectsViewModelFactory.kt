package com.faris.timetable.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.viewmodel.DayOfTheWeekSubjectsVIewModel
import java.lang.IllegalArgumentException

class DayOfTheWeekSubjectsViewModelFactory(private val dao: SubjectDao, private val dayId : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DayOfTheWeekSubjectsVIewModel::class.java)){
            return DayOfTheWeekSubjectsVIewModel(dao, dayId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel!")
    }
}