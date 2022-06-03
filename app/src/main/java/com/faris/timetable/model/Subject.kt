package com.faris.timetable.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "all_subjects_table")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    var subjectId : Long = 0L,
    @ColumnInfo(name = "subject_name")
    var subjectName : String = "",
)
