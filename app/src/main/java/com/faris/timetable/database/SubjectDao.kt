package com.faris.timetable.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.faris.timetable.model.Subject
import com.faris.timetable.model.SubjectWithDay

@Dao
interface SubjectDao {

    @Insert
    suspend fun insertSubject(subjectWithDay: SubjectWithDay)

    @Update
    suspend fun updateSubject(subjectWithDay: SubjectWithDay)

    @Delete
    suspend fun deleteSubject(subjectWithDay: SubjectWithDay)

    @Query("SELECT * FROM subject_table WHERE day_id = :todayId")
    fun findTodaySubjects(todayId: Int): LiveData<List<SubjectWithDay>>


    @Insert
    suspend fun insertSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query("SELECT * FROM all_subjects_table")
    fun getAllSubjects() : LiveData<List<Subject>>
}