package com.faris.timetable.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.faris.timetable.model.Note
import com.faris.timetable.model.Subject
import com.faris.timetable.model.SubjectWithDay

@Dao
interface SubjectDao {

    @Query("DELETE FROM note_table WHERE subject_id=:subjectId")
    suspend fun deleteNotesAfterSubject(subjectId: Long)

    @Insert
    suspend fun insertSubjectWithDay(subjectWithDay: SubjectWithDay)

    @Update
    suspend fun updateSubjectWithDay(subjectWithDay: SubjectWithDay)

    @Delete
    suspend fun deleteSubjectWithDay(subjectWithDay: SubjectWithDay)

    @Query("SELECT * FROM subject_table WHERE day_id = :todayId")
    fun findTodaySubjects(todayId: Int): LiveData<List<SubjectWithDay>>

    @Insert
    suspend fun insertSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query("SELECT * FROM all_subjects_table ORDER BY subjectId DESC")
    fun getAllSubjects(): LiveData<List<Subject>>

    @Query("SELECT * FROM all_subjects_table WHERE subjectId = :subjectId")
    fun getSubject(subjectId: Long): LiveData<Subject>

    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECt * FROM note_table WHERE subject_id=:subjectId ORDER BY noteId DESC")
    fun getNoteForThisSubject(subjectId: Long) : LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE noteId=:noteId")
    suspend fun getNoteById(noteId : Long) : Note

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)


}