package com.faris.timetable.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faris.timetable.model.Note
import com.faris.timetable.model.Subject
import com.faris.timetable.model.SubjectWithDay

@Database(entities = [Subject::class, SubjectWithDay::class, Note::class], version = 3, exportSchema = false)
abstract class SubjectDatabase : RoomDatabase() {
    abstract val subjectDao: SubjectDao

    companion object {
        @Volatile
        private var INSTANCE: SubjectDatabase? = null

        fun getInstance(context: Context): SubjectDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubjectDatabase::class.java,
                        "subject_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}