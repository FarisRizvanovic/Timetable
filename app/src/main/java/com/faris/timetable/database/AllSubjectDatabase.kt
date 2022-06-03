package com.faris.timetable.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faris.timetable.model.Subject

@Database(entities = [Subject::class], version = 1, exportSchema = false)
abstract class AllSubjectDatabase : RoomDatabase() {
    abstract val allSubjectDao: AllSubjectDao

    companion object {
        @Volatile
        private var INSTANCE: AllSubjectDatabase? = null

        fun getInstance(context: Context): AllSubjectDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AllSubjectDatabase::class.java,
                        "all_subjects_table"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}