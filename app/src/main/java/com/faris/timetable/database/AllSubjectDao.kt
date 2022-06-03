package com.faris.timetable.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.faris.timetable.model.Subject

@Dao
interface AllSubjectDao {

    @Insert
    suspend fun insertSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query("SELECT * FROM all_subjects_table")
    fun getAllSubjects() : LiveData<List<Subject>>
}