package com.faris.timetable.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject_table")
data class SubjectWithDay(
    @PrimaryKey(autoGenerate = true)
    var subjectId : Long = 0L,
    @ColumnInfo(name = "subject_name")
    var subjectName : String = "",
    @ColumnInfo(name = "day_id")
    var dayId : Int = 1
)