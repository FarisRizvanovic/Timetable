package com.faris.timetable.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faris.timetable.database.SubjectDao
import com.faris.timetable.model.SubjectWithDay
import java.util.*

class HomeViewModel(private val dao: SubjectDao) : ViewModel() {


    lateinit var todaySubjects : LiveData<List<SubjectWithDay>>


    fun getDayOfTheWeek(): LiveData<String> {
        val calendar = Calendar.getInstance()
        val listOfDays =
            listOf("Nedelja", "Ponedeljak", "Utorak", "Srijeda", "Cetvrtak", "Petak", "Subota")
        val day = listOfDays[calendar.get((Calendar.DAY_OF_WEEK)) - 1]
        var todayID = when (day) {
            "Ponedeljak" -> 1
            "Utorak" -> 2
            "Srijeda" -> 3
            "Cetvrtak" -> 4
            "Petak" -> 5
            else -> 1
        }
        todaySubjects = dao.findTodaySubjects(todayID)

        return MutableLiveData(day)
    }

}