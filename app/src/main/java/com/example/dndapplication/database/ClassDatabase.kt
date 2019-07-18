package com.example.dndapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dndapplication.Models.DndClass

@Database(entities = [DndClass::class], version = 1)
@TypeConverters(Converters::class)
abstract class ClassDatabase : RoomDatabase() {

    abstract fun DndClassDao(): ClassDao

    companion object {
        @Volatile
        private var INSTANCE: ClassDatabase? = null
        const val DATABASE_NAME = "CLASS_DATABASE"

        fun getDatabase(context: Context): ClassDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ClassDatabase::class.java,
                        DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
