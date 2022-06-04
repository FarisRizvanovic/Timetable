package com.faris.timetable.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId : Long = 0L,
    @ColumnInfo(name = "note_name")
    var noteName : String = "",
    @ColumnInfo(name = "note_done")
    var noteDone : Boolean = false,
    @ColumnInfo(name = "subject_id")
    var subjectId : Long = 1
)
